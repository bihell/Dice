package com.bihell.dice.commons.constant;

/**
 * 文件上传路径
 *
 * @author Tang
 */
public class UploadsPrefix {

    private UploadsPrefix() {
    }

    /**
     * 文件上传路径
     */
    public static final String UPLOADS = "/uploads";

    /**
     * 头像上传路径前缀
     */
    public static final String AVATAR_PREFIX = "/avatar";

    /**
     * 头像上传路径
     */
    public static final String AVATAR = UPLOADS + AVATAR_PREFIX;

}
