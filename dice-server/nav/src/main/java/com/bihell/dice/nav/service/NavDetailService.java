package com.bihell.dice.nav.service;

import com.bihell.dice.nav.entity.NavDetail;
import com.bihell.dice.nav.param.NavDetailPageParam;
import com.bihell.dice.framework.common.service.BaseService;
import com.bihell.dice.framework.core.pagination.Paging;

/**
 * 导航明细表 服务类
 *
 * @author tpxcer
 * @since 2021-06-04
 */
public interface NavDetailService extends BaseService<NavDetail> {

    /**
     * 保存
     *
     * @param navDetail
     * @return
     * @throws Exception
     */
    boolean saveNavDetail(NavDetail navDetail) throws Exception;

    /**
     * 修改
     *
     * @param navDetail
     * @return
     * @throws Exception
     */
    boolean updateNavDetail(NavDetail navDetail) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteNavDetail(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param
     * @return
     * @throws Exception
     */
    Paging<NavDetail> getNavDetailPageList(NavDetailPageParam navDetailPageParam) throws Exception;

}
