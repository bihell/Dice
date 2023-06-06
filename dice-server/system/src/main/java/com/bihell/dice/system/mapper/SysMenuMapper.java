package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.system.entity.SysMenu;
import com.bihell.dice.system.param.SysPermissionPageParam;
import com.bihell.dice.system.vo.SysPermissionVo;
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
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysPermissionVo getSysPermissionById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysPermissionPageParam
     * @return
     */
    IPage<SysPermissionVo> getSysPermissionPageList(@Param("page") Page page, @Param("param") SysPermissionPageParam sysPermissionPageParam);

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
    List<SysMenu> getMenuListByUserId(@Param("userId") Long userId);
}
