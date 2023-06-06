package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.system.entity.SysDept;
import com.bihell.dice.system.param.SysDepartmentPageParam;
import com.bihell.dice.system.vo.SysDepartmentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 部门 Mapper 接口
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysDepartmentVo getSysDepartmentById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysDepartmentPageParam
     * @return
     */
    IPage<SysDepartmentVo> getSysDepartmentPageList(@Param("page") Page page, @Param("param") SysDepartmentPageParam sysDepartmentPageParam);

}
