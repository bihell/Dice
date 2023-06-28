package com.bihell.dice.system.convert;

import com.bihell.dice.system.entity.SysMenu;
import com.bihell.dice.system.vo.SysPermissionTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * SysPermission类对象书香转换器
/ *
 * @author geekidea
 * @date 2019-10-26
 **/
@Mapper
public interface SysPermissionConvert {

    SysPermissionConvert INSTANCE = Mappers.getMapper(SysPermissionConvert.class);

    /**
     * SysPermission对象转换成SysPermissionTreeVo对象
     *
     * @param sysMenu
     * @return
     */
    SysPermissionTreeVo permissionToTreeVo(SysMenu sysMenu);

}
