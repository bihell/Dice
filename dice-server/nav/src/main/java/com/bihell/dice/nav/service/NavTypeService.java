package com.bihell.dice.nav.service;

import com.bihell.dice.nav.entity.NavType;
import com.bihell.dice.nav.param.NavTypePageParam;
import com.bihell.dice.framework.common.service.BaseService;
import com.bihell.dice.framework.core.pagination.Paging;

/**
 *  服务类
 *
 * @author tpxcer
 * @since 2021-06-04
 */
public interface NavTypeService extends BaseService<NavType> {

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
     * @param navDetailQueryParam
     * @return
     * @throws Exception
     */
    Paging<NavType> getNavTypePageList(NavTypePageParam navDetailPageParam) throws Exception;

}
