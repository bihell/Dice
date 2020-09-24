package com.bihell.dice.framework.log.service;

import com.bihell.dice.framework.common.service.BaseService;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.entity.SysLoginLog;
import com.bihell.dice.framework.log.param.SysLoginLogPageParam;

/**
 * 系统登录日志 服务类 todo
 */
public interface SysLoginLogService extends BaseService<SysLoginLog> {

    /**
     * 保存
     *
     * @param sysLoginLog
     * @return
     * @throws Exception
     */
    boolean saveSysLoginLog(SysLoginLog sysLoginLog) throws Exception;

    /**
     * 修改
     *
     * @param sysLoginLog
     * @return
     * @throws Exception
     */
    boolean updateSysLoginLog(SysLoginLog sysLoginLog) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysLoginLog(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param sysLoginLogQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysLoginLog> getSysLoginLogPageList(SysLoginLogPageParam sysLoginLogPageParam) throws Exception;

}
