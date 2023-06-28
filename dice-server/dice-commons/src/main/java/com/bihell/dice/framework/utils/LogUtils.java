package com.bihell.dice.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author Tang
 */
public class LogUtils {

    private LogUtils() {
    }

    /**
     * 定义了一个 {@link StackWalker} 实例，使用 {@link StackWalker.Option#RETAIN_CLASS_REFERENCE} 选项来获取调用类的引用。
     * {@link StackWalker} 是一个新的 Java 9 API，可以方便地访问栈帧，并提供了比 {@link Thread#getStackTrace()} 更高效的方法来获取堆栈跟踪信息。
     */
    private static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    /**
     * 获取调用者类
     * <p>
     * <pre>{@code Class<?> callerClass = STACK_WALKER.getCallerClass();}</pre>
     * <pre>{@code Class<?> callerClass = STACK_WALKER.walk(frames -> frames.skip(1).findFirst().orElseThrow()).getDeclaringClass();}</pre>
     *
     * @return 调用者类
     */
    public static Class<?> getCallerClass() {
        return STACK_WALKER.getCallerClass();
    }

    /**
     * 获取 {@link Logger}
     *
     * @return {@link Logger}
     */
    public static Logger getLogger() {
        return LoggerFactory.getLogger(STACK_WALKER.getCallerClass());
    }

    /**
     * Log a message at the TRACE level.
     *
     * @param msg the message string to be logged
     */
    public static void trace(String msg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).trace(msg);
    }

    /**
     * Log a message at the TRACE level according to the specified format
     * and argument.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the TRACE level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void trace(String format, Object arg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).trace(format, arg);
    }

    /**
     * Log a message at the TRACE level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the TRACE level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void trace(String format, Object arg1, Object arg2) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).trace(format, arg1, arg2);
    }

    /**
     * Log a message at the TRACE level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the TRACE level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for TRACE. The variants taking {@link #trace(String, Object) one} and
     * {@link #trace(String, Object, Object) two} arguments exist solely in order to avoid this hidden cost.
     *
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    public static void trace(String format, Object... arguments) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).trace(format, arguments);
    }

    /**
     * Log an exception (throwable) at the TRACE level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void trace(String msg, Throwable t) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).trace(msg, t);
    }

    /**
     * Log a message at the DEBUG level.
     *
     * @param msg the message string to be logged
     */
    public static void debug(String msg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).debug(msg);
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and argument.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the DEBUG level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void debug(String format, Object arg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).debug(format, arg);
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the DEBUG level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void debug(String format, Object arg1, Object arg2) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).debug(format, arg1, arg2);
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the DEBUG level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for DEBUG. The variants taking
     * {@link #debug(String, Object) one} and {@link #debug(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.
     *
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    public static void debug(String format, Object... arguments) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).debug(format, arguments);
    }

    /**
     * Log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void debug(String msg, Throwable t) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).debug(msg, t);
    }

    /**
     * Log a message at the INFO level.
     *
     * @param msg the message string to be logged
     */
    public static void info(String msg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).info(msg);
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and argument.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the INFO level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void info(String format, Object arg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).info(format, arg);
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the INFO level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void info(String format, Object arg1, Object arg2) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).info(format, arg1, arg2);
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the INFO level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for INFO. The variants taking
     * {@link #info(String, Object) one} and {@link #info(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.
     *
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    public static void info(String format, Object... arguments) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).info(format, arguments);
    }

    /**
     * Log an exception (throwable) at the INFO level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void info(String msg, Throwable t) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).info(msg, t);
    }

    /**
     * Log a message at the WARN level.
     *
     * @param msg the message string to be logged
     */
    public static void warn(String msg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).warn(msg);
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and argument.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the WARN level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void warn(String format, Object arg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).warn(format, arg);
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the WARN level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for WARN. The variants taking
     * {@link #warn(String, Object) one} and {@link #warn(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.
     *
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    public static void warn(String format, Object... arguments) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).warn(format, arguments);
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the WARN level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void warn(String format, Object arg1, Object arg2) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).warn(format, arg1, arg2);
    }

    /**
     * Log an exception (throwable) at the WARN level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void warn(String msg, Throwable t) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).warn(msg, t);
    }

    /**
     * Log a message at the ERROR level.
     *
     * @param msg the message string to be logged
     */
    public static void error(String msg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).error(msg);
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and argument.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the ERROR level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void error(String format, Object arg) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).error(format, arg);
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the ERROR level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void error(String format, Object arg1, Object arg2) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).error(format, arg1, arg2);
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and arguments.
     *
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the ERROR level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for ERROR. The variants taking
     * {@link #error(String, Object) one} and {@link #error(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.
     *
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    public static void error(String format, Object... arguments) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).error(format, arguments);
    }

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void error(String msg, Throwable t) {
        LoggerFactory.getLogger(STACK_WALKER.getCallerClass()).error(msg, t);
    }

}
