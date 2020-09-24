package com.bihell.dice.framework.shiro.convert;

import com.bihell.dice.framework.shiro.jwt.JwtToken;
import com.bihell.dice.framework.shiro.vo.JwtTokenRedisVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Shiro包下使用mapstruct对象属性复制转换器 todo
 **/
@Mapper
public interface ShiroMapstructConvert {

    ShiroMapstructConvert INSTANCE = Mappers.getMapper(ShiroMapstructConvert.class);

    /**
     * JwtToken对象转换成JwtTokenRedisVo
     *
     * @param jwtToken
     * @return
     */
    JwtTokenRedisVo jwtTokenToJwtTokenRedisVo(JwtToken jwtToken);

}
