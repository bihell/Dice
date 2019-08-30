package com.bihell.dice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.mapper.LogMapper;
import com.bihell.dice.model.domain.Log;
import com.bihell.dice.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 日志 Service 实现类
 *
 * @author bihell
 * @since 2017/10/11 10:42
 */
@Service("logsService")
@Transactional(rollbackFor = Throwable.class)
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void save(String action, String data, String message, String type) {
        this.save(action, data, message, type, null, null);
    }

    @Override
    public void save(String action, String data, String message, String type, String ip) {
        this.save(action, data, message, type, ip, null);
    }

    @Override
    public void save(String action, String data, String message, String type, String ip, Integer userId) {
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
     * @param current  当前分页
     * @param limit 分页大小
     * @return Page<Log>
     */
    @Override
    public IPage<Log> getLogs(Integer current, Integer limit) {
        Page<Log> page = new Page<>(current,limit);
        return logMapper.selectPage(page,null);
    }

}
