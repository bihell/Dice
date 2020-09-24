package com.bihell.dice.framework.core.util;


import com.bihell.dice.framework.core.bean.RequestDetail;

/**
 * 记录请求详情信息到当前线程中，可在任何地方获取 todo
 **/
public class RequestDetailThreadLocal {

    private static ThreadLocal<RequestDetail> threadLocal = new ThreadLocal<>();

    /**
     * 设置请求信息到当前线程中
     *
     * @param requestDetail
     */
    public static void setRequestDetail(RequestDetail requestDetail) {
        threadLocal.set(requestDetail);
    }

    /**
     * 从当前线程中获取请求信息
     */
    public static RequestDetail getRequestDetail() {
        return threadLocal.get();
    }

    /**
     * 销毁
     */
    public static void remove() {
        threadLocal.remove();
    }

}
