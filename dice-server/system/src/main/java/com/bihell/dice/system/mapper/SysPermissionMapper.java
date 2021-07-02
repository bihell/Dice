package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.system.entity.SysPermission;
import com.bihell.dice.system.param.SysPermissionPageParam;
import com.bihell.dice.system.vo.SysPermissionQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 系统权限 Mapper 接口
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysPermissionQueryVo getSysPermissionById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysPermissionPageParam
     * @return
     */
    IPage<SysPermissionQueryVo> getSysPermissionPageList(@Param("page") Page page, @Param("param") SysPermissionPageParam sysPermissionPageParam);

    /**
     * 根据用户id获取该用户所有权限编码
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<String> getPermissionCodesByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id获取菜单列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> getMenuListByUserId(@Param("userId") Long userId);
}
