package com.bihell.dice.framework.core.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Filter请求详情信息 todo
 **/
@Data
@Accessors(chain = true)
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 2543641512850125440L;

	/**
     * 请求ip地址
     */
    private String ip;

    /**
     * 请求路径
     */
    private String path;

}
