package com.bihell.dice.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.bihell.dice.config.constant.CommonRedisKey;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.config.properties.JwtProperties;
import com.bihell.dice.framework.shiro.cache.LoginRedisService;
import com.bihell.dice.framework.shiro.jwt.JwtToken;
import com.bihell.dice.framework.shiro.util.JwtTokenUtil;
import com.bihell.dice.framework.shiro.util.JwtUtil;
import com.bihell.dice.framework.shiro.util.SaltUtil;
import com.bihell.dice.framework.shiro.vo.LoginSysUserVo;
import com.bihell.dice.framework.shiro.vo.RoleInfoVO;
import com.bihell.dice.framework.util.PasswordUtil;
import com.bihell.dice.system.entity.SysDept;
import com.bihell.dice.system.entity.SysRole;
import com.bihell.dice.system.entity.SysUser;
import com.bihell.dice.system.entity.SysUserRole;
import com.bihell.dice.system.enums.StateEnum;
import com.bihell.dice.system.exception.VerificationCodeException;
import com.bihell.dice.system.param.LoginParam;
import com.bihell.dice.system.service.*;
import com.bihell.dice.system.vo.LoginSysUserTokenVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录服务实现类
 * </p>
 *
 * @author haseochen*/
@Api
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginServiceImpl implements LoginService {

    @Lazy
    private final  LoginRedisService loginRedisService;
    @Lazy
    private final  JwtProperties jwtProperties;
    @Lazy
    private final  DiceProperties diceProperties;
    @Lazy
    private final  RedisTemplate redisTemplate;

    @Lazy
    @Autowired
    private SysDeptService sysDeptService;

    @Lazy
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Lazy
    @Autowired
    private SysRoleService sysRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginSysUserTokenVo login(LoginParam loginParam) throws Exception {
        // 校验验证码
        checkVerifyCode(loginParam.getVerifyToken(), loginParam.getCode());

        String username = loginParam.getUsername();
        // 从数据库中获取登录用户信息
        SysUser sysUser = new SysUser().selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username).or().eq(SysUser::getEmail, username));;
        if (sysUser == null) {
            log.error("登录失败,loginParam:{}", loginParam);
            throw new AuthenticationException("用户名或密码错误");
        }
        if (StateEnum.DISABLE.getCode().equals(sysUser.getStatus())) {
            throw new AuthenticationException("账号已禁用");
        }

        // 实际项目中，前端传过来的密码应先加密
        // 原始密码明文：123456
        // 原始密码前端加密：sha256(123456)
        // 后台加密规则：sha256(sha256(123456) + salt)
        String encryptPassword = PasswordUtil.encrypt(loginParam.getPassword(), sysUser.getSalt());
        if (!encryptPassword.equals(sysUser.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        // 将系统用户对象转换成登录用户对象
        LoginSysUserVo loginSysUserVo =  BeanUtil.copyProperties(sysUser,LoginSysUserVo.class);

        // 获取部门
        SysDept sysDept = sysDeptService.getById(sysUser.getDeptId());
        if (sysDept == null) {
            throw new AuthenticationException("部门不存在");
        }
        if (!StateEnum.ENABLE.getCode().equals(sysDept.getStatus())) {
            throw new AuthenticationException("部门已禁用");
        }
        loginSysUserVo.setDepartmentId(sysDept.getId())
                .setDepartmentName(sysDept.getDeptName());

        // 获取当前用户角色
        List<SysUserRole> sysUserRoles = new SysUserRole().selectList(new QueryWrapper<SysUserRole>().lambda()
                .eq(SysUserRole::getUserId, sysUser.getId()));

        List<RoleInfoVO> roleInfoVOList = sysUserRoles.stream().map(
                item -> {
                    SysRole sysRole = sysRoleService.getById(item.getRoleId());
                    if (sysRole == null) {
                        throw new AuthenticationException("角色不存在");
                    }
                    if (StateEnum.DISABLE.getCode().equals(sysRole.getStatus())) {
                        throw new AuthenticationException("角色已禁用");
                    }
                    RoleInfoVO roleInfoVO = new RoleInfoVO();
                    roleInfoVO.setRoleName(sysRole.getRoleName());
                    roleInfoVO.setValue(sysRole.getRoleCode());
                    roleInfoVO.setId(sysRole.getId());
                    return roleInfoVO;
                }
        ).collect(Collectors.toList());

        loginSysUserVo.setRoles(roleInfoVOList);

        // 获取当前用户权限
        Set<String> permissionCodes = sysRoleMenuService.getPermissionCodesByRoleId(sysUserRoles);
        if (CollectionUtils.isEmpty(permissionCodes)) {
            throw new AuthenticationException("权限列表不能为空");
        }
        loginSysUserVo.setPermissionCodes(permissionCodes);

        // 获取数据库中保存的盐值
        String newSalt = SaltUtil.getSalt(sysUser.getSalt(), jwtProperties);

        // 生成token字符串并返回
        Long expireSecond = jwtProperties.getExpireSecond();
        String token = JwtUtil.generateToken(username, newSalt, Duration.ofSeconds(expireSecond));
        log.debug("token:{}", token);

        // 创建AuthenticationToken
        JwtToken jwtToken = JwtToken.build(token, username, newSalt, expireSecond);

        boolean enableShiro = diceProperties.getShiro().isEnable();
        if (enableShiro) {
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            // 执行认证登录
            subject.login(jwtToken);
        } else {
            log.warn("未启用Shiro");
        }

        // 缓存登录信息到Redis
        loginRedisService.cacheLoginInfo(jwtToken, loginSysUserVo);
        log.debug("登录成功,username:{}", username);

        // 更新最后登陆时间
        sysUser.setLoginDate(LocalDateTime.now());
        sysUser.updateById();

        // 缓存登录信息到redis
        String tokenSha256 = DigestUtils.sha256Hex(token);
        redisTemplate.opsForValue().set(tokenSha256, loginSysUserVo, 1, TimeUnit.DAYS);

        // 返回token和登录用户信息对象
        LoginSysUserTokenVo loginSysUserTokenVo = new LoginSysUserTokenVo();
        loginSysUserTokenVo.setToken(token);
        loginSysUserTokenVo.setLoginSysUserVo(loginSysUserVo);
        return loginSysUserTokenVo;
    }

    @Override
    // todo
    public void checkVerifyCode(String verifyToken, String code) {
        // 如果没有启用验证码，则返回
        if (!diceProperties.isEnableVerifyCode()) {
            return;
        }
        // 校验验证码
        if (StringUtils.isBlank(code)) {
            throw new VerificationCodeException("请输入验证码");
        }
        // 从redis中获取
        String redisKey = String.format(CommonRedisKey.VERIFY_CODE, verifyToken);
        String generateCode = (String) redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isBlank(generateCode)) {
            throw new VerificationCodeException("验证码已过期或不正确");
        }
        // 不区分大小写
        if (!generateCode.equalsIgnoreCase(code)) {
            throw new VerificationCodeException("验证码错误");
        }
        // 验证码校验成功，删除Redis缓存
        redisTemplate.delete(redisKey);
    }

    @Override
    public void logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        // 获取token
        String token = JwtTokenUtil.getToken(request);
        String username = JwtUtil.getUsername(token);
        // 删除Redis缓存信息
        loginRedisService.deleteLoginInfo(token, username);
        log.info("登出成功,username:{},token:{}", username, token);
    }
}
