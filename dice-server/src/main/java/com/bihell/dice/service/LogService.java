package com.bihell.dice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.model.domain.Log;
import com.bihell.dice.model.enums.LogType;

/**
 * 日志 Service 接口
 *
 * @author bihell
 * @since 2017/10/11 10:42
 */
public interface LogService {

    /**
     * 保存操作日志
     *
     * @param data    日志数据
     * @param message 日志信息
     * @param type    日志类型
     */
    void save(String data, String message, LogType type);

    /**
     * 保存操作日志
     *
     * @param action  日志动作
     * @param data    日志数据
     * @param message 日志信息
     * @param type    日志类型
     */
    void save(String action, String data, String message, LogType type);

    /**
     * 保存操作日志
     *
     * @param action  日志动作
     * @param data    日志数据
     * @param message 日志信息
     * @param type    日志类型
     * @param ip      操作人ip
     */
    void save(String action, String data, String message, LogType type, String ip);

    /**
     * 保存操作日志
     *
     * @param action  日志动作
     * @param data    日志数据
     * @param message 日志信息
     * @param type    日志类型
     * @param ip      操作人ip
     * @param userId  操作人id
     */
    void save(String action, String data, String message, LogType type, String ip, Integer userId);

    /**
     * 获取日志
     *
     * @param current  当前分页
     * @param limit 分页大小
     * @return Page<Log>
     */
    IPage<Log> getLogs(Integer current, Integer limit);

    /**
     * 保存操作日志
     *
     * @param data    日志数据
     * @param message 日志信息
     * @param type    日志类型
     * @param ip      操作人ip
     * @param userId  操作人id
     */
    void save(String data, String message, LogType type, String ip, Integer userId);
}
