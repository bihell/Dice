package com.bihell.dice.controller;

import com.bihell.dice.exception.NotFoundException;
import com.bihell.dice.exception.NotLoginException;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.utils.ErrorCode;
import com.bihell.dice.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
  常见异常处理并返回相应错误码
  SpringMVC自定义异常对应的status code
  Exception                               HTTP Status Code
  ConversionNotSupportedException         500 (Internal Server Error)
  HttpMessageNotWritableException         500 (Internal Server Error)
  HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
  HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
  HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
  NoSuchRequestHandlingMethodException    404 (Not Found)
  TypeMismatchException                   400 (Bad Request)
  HttpMessageNotReadableException         400 (Bad Request)
  MissingServletRequestParameterException 400 (Bad Request)
 */

/**
 * 全局异常处理 Controller
 *
 * @author bihell
 * @since 2017/8/30 12:25
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Tip异常返回
     *
     * @param req {@link HttpServletRequest}
     * @param e   {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(value = TipException.class)
    public RestResponse tipErrorHandler(HttpServletRequest req, TipException e) {
        return RestResponse.fail(e.getMessage());
    }

    /**
     * NotFound异常返回
     *
     * @param req {@link HttpServletRequest}
     * @param e   {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(value = NotFoundException.class)
    public RestResponse NotFoundErrorHandler(HttpServletRequest req, NotFoundException e) {
        String message = "";
        if (null != e.getClz()) {
            message = e.getClz().getSimpleName();
        }
        message += "资源不存在!";
        return RestResponse.fail(ErrorCode.NOT_FOUND.getCode(), message);
    }

    /**
     * NotLogin异常返回
     *
     * @param req {@link HttpServletRequest}
     * @param e   {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(value = NotLoginException.class)
    public RestResponse NotLoginErrorHandler(HttpServletRequest req, HttpServletResponse rep, NotLoginException e) {
        return RestResponse.fail(ErrorCode.NOT_LOGIN.getCode(), ErrorCode.NOT_LOGIN.getMsg());
    }

    /**
     * 运行时异常
     *
     * @param req {@link HttpServletRequest}
     * @param rep {@link HttpServletResponse}
     * @param re  {@link RuntimeException}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(RuntimeException.class)
    public RestResponse runtimeExceptionHandler(HttpServletRequest req, HttpServletResponse rep, RuntimeException re) {
        log.error("---RuntimeException Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), re.getMessage(), re);
        rep.setStatus(ErrorCode.RUNTIME.getCode());
        return RestResponse.fail(ErrorCode.RUNTIME.getCode(), ErrorCode.RUNTIME.getMsg());
    }

    /**
     * 空指针异常
     *
     * @param req {@link HttpServletRequest}
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link NullPointerException}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(NullPointerException.class)
    public RestResponse nullPointerExceptionHandler(HttpServletRequest req, HttpServletResponse rep, NullPointerException ex) {
        log.error("---NullPointerException Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.NULL_POINTER.getCode());
        return RestResponse.fail(ErrorCode.NULL_POINTER.getCode(), ErrorCode.NULL_POINTER.getMsg());
    }

    /**
     * 类型转换异常
     *
     * @param req {@link HttpServletRequest}
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link ClassCastException}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(ClassCastException.class)
    public RestResponse classCastExceptionHandler(HttpServletRequest req, HttpServletResponse rep, ClassCastException ex) {
        log.error("---classCastException Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.CLASS_CAST.getCode());
        return RestResponse.fail(ErrorCode.CLASS_CAST.getCode(), ErrorCode.CLASS_CAST.getMsg());
    }

    /**
     * IO异常
     *
     * @param req {@link HttpServletRequest}
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link IOException}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(IOException.class)
    public RestResponse classCastExceptionHandler(HttpServletRequest req, HttpServletResponse rep, IOException ex) {
        log.error("---classCastException Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.IO.getCode());
        return RestResponse.fail(ErrorCode.IO.getCode(), ErrorCode.IO.getMsg());
    }

    /**
     * 未知方法异常
     *
     * @param req {@link HttpServletRequest}
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link NoSuchMethodException}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public RestResponse noSuchMethodExceptionHandler(HttpServletRequest req, HttpServletResponse rep, NoSuchMethodException ex) {
        log.error("---noSuchMethodException Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.NO_SUCH_METHOD.getCode());
        return RestResponse.fail(ErrorCode.NO_SUCH_METHOD.getCode(), ErrorCode.NO_SUCH_METHOD.getMsg());
    }

    /**
     * 数组越界异常
     *
     * @param req {@link HttpServletRequest}
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link IndexOutOfBoundsException}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public RestResponse indexOutOfBoundsExceptionHandler(HttpServletRequest req, HttpServletResponse rep, IndexOutOfBoundsException ex) {
        log.error("---indexOutOfBoundsException Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.INDEX_OUTOF_BOUNDS.getCode());
        return RestResponse.fail(ErrorCode.INDEX_OUTOF_BOUNDS.getCode(), ErrorCode.INDEX_OUTOF_BOUNDS.getMsg());
    }

    /**
     * 400相关异常
     *
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, TypeMismatchException.class, MissingServletRequestParameterException.class})
    public RestResponse request400(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        log.error("---request400 Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.BAD_REQUEST.getCode());
        return RestResponse.fail(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMsg());
    }

    /**
     * 404异常
     *
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public RestResponse request404(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        log.error("---request404 Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.NOT_FOUND.getCode());
        return RestResponse.fail(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMsg());
    }

    /**
     * 405相关异常
     *
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestResponse request405(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        log.error("---request405 Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.METHOD_BOT_ALLOWED.getCode());
        return RestResponse.fail(ErrorCode.METHOD_BOT_ALLOWED.getCode(), ErrorCode.METHOD_BOT_ALLOWED.getMsg());
    }

    /**
     * 406相关异常
     *
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public RestResponse request406(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        log.error("---request406 Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.NOT_ACCEPTABLE.getCode());
        return RestResponse.fail(ErrorCode.NOT_ACCEPTABLE.getCode(), ErrorCode.NOT_ACCEPTABLE.getMsg());
    }

    /**
     * 500相关异常
     *
     * @param rep {@link HttpServletResponse}
     * @param ex  {@link Exception}
     * @return {@link RestResponse}
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public RestResponse server500(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        log.error("---server500 Handler---Host {}, invokes url {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ex.getMessage(), ex);
        rep.setStatus(ErrorCode.INTERNAL_SERVER_ERROR.getCode());
        return RestResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMsg());
    }

    /**
     * 全局异常返回
     *
     * @param req {@link HttpServletRequest}
     * @param e   {@link HttpServletResponse}
     * @return {@link Exception}
     */
    @ExceptionHandler(value = Exception.class)
    public RestResponse defaultErrorHandler(HttpServletRequest req, HttpServletResponse rep, Exception e) {
        log.error("---DefaultException Handler---Host {}, invokes url {}, ERROR TYPE: {},  ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getClass(), e.getMessage());
        e.printStackTrace();
        return RestResponse.fail(e.getMessage());
    }
}
