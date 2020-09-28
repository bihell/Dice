package com.bihell.dice.system.convert;

import com.bihell.dice.framework.shiro.vo.LoginSysUserVo;
import com.bihell.dice.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户对象属性转换器
 **/
@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    /**
     * 系统用户实体对象转换成登录用户VO对象
     *
     * @param user
     * @return
     */
    LoginSysUserVo sysUserToLoginSysUserVo(User user);

}
