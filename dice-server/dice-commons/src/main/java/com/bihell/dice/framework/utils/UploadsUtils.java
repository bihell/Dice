package com.bihell.dice.framework.utils;

import com.bihell.dice.framework.constant.UploadsPrefix;
import com.bihell.dice.framework.properties.DiceProperties;
import com.bihell.dice.framework.exception.file.MaxFileNameLengthException;
import com.bihell.dice.framework.exception.file.MaxFileSizeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 文件上传工具类
 *
 * @author Tang
 */
public class UploadsUtils {

    private UploadsUtils() {
    }

    private static final DiceProperties DICE_PROPERTIES = SpringUtils.getBean(DiceProperties.class);

    /**
     * 文件名最大长度
     */
    private static final int MAX_FILE_NAME_LENGTH = 128;

    /**
     * 文件最大大小
     */
    private static final long MAX_FILE_SIZE = 2L * 1024L * 1024L;

    /**
     * 获取文件上传路径
     *
     * @return 文件上传路径
     */
    public static String getUploads() {
        return DICE_PROPERTIES.getUploadFolder();
    }

    /**
     * 获取头像上传路径
     *
     * @return 头像上传路径
     */
    public static String getAvatar() {
        return getUploads() + UploadsPrefix.AVATAR_PREFIX;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件路径
     */
    public static String upload(MultipartFile file) {
        var filePath = upload(file, getUploads());
        return filePath.replace(getUploads(), UploadsPrefix.UPLOADS);
    }

    /**
     * 上传头像
     *
     * @param avatar 头像文件
     * @return 文件路径
     */
    public static String uploadAvatar(MultipartFile avatar) {
        var avatarPath = upload(avatar, getAvatar());
        return avatarPath.replace(getAvatar(), UploadsPrefix.AVATAR);
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件路径
     */
    private static String upload(MultipartFile file, String path) {
        var fileName = file.getOriginalFilename();
        if (Objects.isNull(fileName)) {
            fileName = "unknown-file-name";
        }
        if (fileName.length() > MAX_FILE_NAME_LENGTH) {
            throw new MaxFileNameLengthException("上传失败, 文件名最大长度为: " + MAX_FILE_NAME_LENGTH);
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new MaxFileSizeException("上传失败, 文件最大大小为: " + ByteUtils.getSize(MAX_FILE_SIZE));
        }
        fileName = System.currentTimeMillis() + "_" + fileName;
        var destDir = new File(path);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        var filePath = destDir + File.separator + fileName;
        var dest = new File(filePath);
        try {
            file.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
