package com.bihell.dice.framework.utils;


import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public final class HttpServletResponseUtil {

    private static String UTF8 = "UTF-8";
    private static String CONTENT_TYPE = "application/json";

    private HttpServletResponseUtil(){
        throw new AssertionError();
    }

    public static void printJson(HttpServletResponse response, Object object) throws Exception{
        response.setCharacterEncoding(UTF8);
        response.setContentType(CONTENT_TYPE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(object));
        printWriter.flush();
        printWriter.close();
    }
}
