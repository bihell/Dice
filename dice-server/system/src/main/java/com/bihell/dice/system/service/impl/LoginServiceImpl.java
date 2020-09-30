package com.bihell.dice.system.service.impl;

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
import com.bihell.dice.framework.util.PasswordUtil;
import com.bihell.dice.system.convert.SysUserConvert;
import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.exception.VerificationCodeException;
import com.bihell.dice.system.param.LoginParam;
import com.bihell.dice.system.service.LoginService;
import com.bihell.dice.system.vo.LoginSysUserTokenVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
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
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 登录服务实现类
 * </p>
 **/
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginSysUserTokenVo login(LoginParam loginParam) {
        // 校验验证码
        checkVerifyCode(loginParam.getVerifyToken(), loginParam.getCode());

        String username = loginParam.getUsername();
        // 从数据库中获取登录用户信息
        User sysUser = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username).or().eq(User::getEmail, username));
        if (sysUser == null) {
            log.error("登录失败,loginParam:{}", loginParam);
            throw new AuthenticationException("用户名或密码错误");
        }
        // todo
        // if (StateEnum.DISABLE.getCode().equals(sysUser.getState())) {
        //     throw new AuthenticationException("账号已禁用");
        // }

        // 实际项目中，前端传过来的密码应先加密
        // 原始密码明文：123456
        // 原始密码前端加密：sha256(123456)
        // 后台加密规则：sha256(sha256(123456) + salt)
        String encryptPassword = PasswordUtil.encrypt(loginParam.getPassword(), sysUser.getSalt());
        if (!encryptPassword.equals(sysUser.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        // 将系统用户对象转换成登录用户对象
        LoginSysUserVo loginSysUserVo = SysUserConvert.INSTANCE.sysUserToLoginSysUserVo(sysUser);

        // 获取部门 todo
//        SysDepartment sysDepartment = sysDepartmentService.getById(sysUser.getDepartmentId());
//        if (sysDepartment == null) {
//            throw new AuthenticationException("部门不存在");
//        }
//        if (!StateEnum.ENABLE.getCode().equals(sysDepartment.getState())) {
//            throw new AuthenticationException("部门已禁用");
//        }
//        loginSysUserVo.setDepartmentId(sysDepartment.getId())
//                .setDepartmentName(sysDepartment.getName());

        // 获取当前用户角色 todo
//        Long roleId = sysUser.getRoleId();
//        SysRole sysRole = sysRoleService.getById(roleId);
//        if (sysRole == null) {
//            throw new AuthenticationException("角色不存在");
//        }
//        if (StateEnum.DISABLE.getCode().equals(sysRole.getState())) {
//            throw new AuthenticationException("角色已禁用");
//        }
//        loginSysUserVo.setRoleId(sysRole.getId())
//                .setRoleName(sysRole.getName())
//                .setRoleCode(sysRole.getCode());

        // 获取当前用户权限 todo
//        Set<String> permissionCodes = sysRolePermissionService.getPermissionCodesByRoleId(roleId);
//        if (CollectionUtils.isEmpty(permissionCodes)) {
//            throw new AuthenticationException("权限列表不能为空");
//        }
//        loginSysUserVo.setPermissionCodes(permissionCodes);

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
        sysUser.setLogged(LocalDateTime.now());
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
