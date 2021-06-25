package com.bihell.dice.nav.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bihell.dice.nav.entity.NavType;
import com.bihell.dice.nav.param.NavTypePageParam;
import com.bihell.dice.framework.common.service.BaseService;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.nav.vo.NavTypeVo;

import java.util.List;

/**
 *  服务类
 *
 * @author tpxcer
 * @since 2021-06-04
 */
public interface NavTypeService extends IService<NavType> {

    /**
     * 保存
     *
     * @param navDetail
     * @return
     * @throws Exception
     */
    boolean saveNavType(NavType navDetail) throws Exception;

    /**
     * 修改
     *
     * @param navDetail
     * @return
     * @throws Exception
     */
    boolean updateNavType(NavType navDetail) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteNavType(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param
     * @return
     * @throws Exception
     */
    Paging<NavType> getNavTypePageList(NavTypePageParam navDetailPageParam) throws Exception;

    /**
     * 获取导航分类列表
     * @return
     */
    List<NavType> getAllNavTypeList(NavTypePageParam navTypePageParam);

    /**
     * 获取导航分类树形列表
     * @return
     */
    List<NavTypeVo> getNavTypeTree(NavTypePageParam navTypePageParam);

}
