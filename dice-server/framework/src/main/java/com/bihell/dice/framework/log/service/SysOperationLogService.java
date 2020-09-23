package com.bihell.dice.framework.log.service;

import com.bihell.dice.framework.common.service.BaseService;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.entity.SysOperationLog;
import com.bihell.dice.framework.log.param.SysOperationLogPageParam;

/**
 * 系统操作日志 服务类 todo
 */
public interface SysOperationLogService extends BaseService<SysOperationLog> {

    /**
     * 保存
     *
     * @param sysOperationLog
     * @return
     * @throws Exception
     */
    boolean saveSysOperationLog(SysOperationLog sysOperationLog) throws Exception;

    /**
     * 修改
     *
     * @param sysOperationLog
     * @return
     * @throws Exception
     */
    boolean updateSysOperationLog(SysOperationLog sysOperationLog) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysOperationLog(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param sysOperationLogQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysOperationLog> getSysOperationLogPageList(SysOperationLogPageParam sysOperationLogPageParam) throws Exception;

}
