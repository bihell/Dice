package com.bihell.dice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.mapper.LogMapper;
import com.bihell.dice.model.domain.Log;
import com.bihell.dice.model.enums.LogType;
import com.bihell.dice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志 Service 实现类
 *
 * @author bihell
 * @since 2017/10/11 10:42
 */
@Service("logsService")
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    /**
     * 保存操作日志
     *
     * @param data    日志数据
     * @param message 日志信息
     * @param type    日志类型
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(String data, String message, LogType type) {
        this.save(data, message, type, null, null);
    }

    @Override
    public void save(String action, String data, String message, LogType type) {
        this.save(action, data, message, type, null, null);
    }

    @Override
    public void save(String action, String data, String message, LogType type, String ip) {
        this.save(action, data, message, type, ip, null);
    }

    @Override
    public void save(String action, String data, String message, LogType type, String ip, Integer userId) {
        Log log = new Log();
        log.setAction(action);
        log.setData(data);
        log.setMessage(message);
        log.setType(type);
        log.setIp(ip);
        log.setUserId(userId);
        log.insert();
    }

    /**
     * 获取日志
     *
     * @param current 当前分页
     * @param limit   分页大小
     * @return Page<Log>
     */
    @Override
    public IPage<Log> getLogs(Integer current, Integer limit) {
        Page<Log> page = new Page<>(current, limit);
        return logMapper.selectPage(page, null);
    }

    /**
     * 保存操作日志
     *
     * @param data    日志数据
     * @param message 日志信息
     * @param type    日志类型
     * @param ip      操作人ip
     * @param userId  操作人id
     */
    @Override
    public void save(String data, String message, LogType type, String ip, Integer userId) {
        Log log = new Log();
        log.setData(data);
        log.setMessage(message);
        log.setType(type);
        log.setIp(ip);
        log.setUserId(userId);
        log.insert();
    }

}
