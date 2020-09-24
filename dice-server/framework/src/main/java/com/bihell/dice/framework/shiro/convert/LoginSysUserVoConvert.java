package com.bihell.dice.framework.shiro.convert;

import com.bihell.dice.framework.shiro.vo.LoginSysUserRedisVo;
import com.bihell.dice.framework.shiro.vo.LoginSysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 登录系统用户VO对象属性复制转换器 todo
 **/
@Mapper
public interface LoginSysUserVoConvert {

    LoginSysUserVoConvert INSTANCE = Mappers.getMapper(LoginSysUserVoConvert.class);

    /**
     * LoginSysUserVo对象转换成LoginSysUserRedisVo
     *
     * @param loginSysUserVo
     * @return
     */
    LoginSysUserRedisVo voToRedisVo(LoginSysUserVo loginSysUserVo);

}
