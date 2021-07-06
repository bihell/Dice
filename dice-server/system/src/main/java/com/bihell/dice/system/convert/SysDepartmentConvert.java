package com.bihell.dice.system.convert;

import com.bihell.dice.system.entity.SysDepartment;
import com.bihell.dice.system.vo.SysDepartmentTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 部门对象属性转换器
 *
 * @author geekidea
 * @date 2019-11-01
 **/
@Mapper
public interface SysDepartmentConvert {

    SysDepartmentConvert INSTANCE = Mappers.getMapper(SysDepartmentConvert.class);

    /**
     * SysDepartment转换成SysDepartmentTreeVo对象
     *
     * @param sysDepartment
     * @return
     */
    SysDepartmentTreeVo entityToTreeVo(SysDepartment sysDepartment);

    /**
     * SysDepartment列表转换成SysDepartmentTreeVo列表
     *
     * @param list
     * @return
     */
    List<SysDepartmentTreeVo> listToTreeVoList(List<SysDepartment> list);

}
