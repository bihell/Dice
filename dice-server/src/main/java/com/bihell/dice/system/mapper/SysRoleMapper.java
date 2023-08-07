package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.system.entity.SysRole;
import com.bihell.dice.system.param.sysrole.SysRolePageParam;
import com.bihell.dice.system.vo.SysRoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 系统角色 Mapper 接口
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysRoleVo getSysRoleById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysRolePageParam
     * @return
     */
    IPage<SysRoleVo> getSysRolePageList(@Param("page") Page page, @Param("param") SysRolePageParam sysRolePageParam);

    //条件分页查询,XML调用demo，实际未使用
    IPage<SysRole> selectPageDemo(Page<SysRole> pageParam,@Param("vo") SysRoleVo sysRoleVo);

    /**
     * 根据用户主键获取角色列表
     *
     * @param userId 用户主键
     * @return 角色列表
     */
    List<SysRole> selectRoleListByUserId(Long userId);

}
