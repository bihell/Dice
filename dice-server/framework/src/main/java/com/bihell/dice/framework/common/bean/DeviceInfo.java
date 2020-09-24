package com.bihell.dice.framework.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 设备信息 todo
 * </p>
 **/
@Data
public class DeviceInfo implements Serializable {
    private static final long serialVersionUID = -5912785220335057555L;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备型号
     */
    private String model;
}
