package com.bihell.dice.framework.ip.service;

import com.bihell.dice.framework.common.service.BaseService;
import com.bihell.dice.framework.ip.entity.IpAddress;

/**
 * IP地址 服务类 todo
 */
public interface IpAddressService extends BaseService<IpAddress> {

    /**
     * 通过ip地址获取IP对象
     *
     * @param ip
     * @return
     */
    IpAddress getByIp(String ip);

    /**
     * 通过ip地址获取区域
     *
     * @param ip
     * @return
     */
    String getAreaByIp(String ip);

    /**
     * 通过ip地址获取运营商
     *
     * @param ip
     * @return
     */
    String getOperatorByIp(String ip);

}
