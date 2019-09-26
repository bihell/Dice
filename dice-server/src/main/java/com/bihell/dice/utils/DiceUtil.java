package com.bihell.dice.utils;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.model.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;

/**
 * 公用工具类
 *
 * @author bihell
 * @since 2017/7/9 22:08
 */
@Slf4j
public class DiceUtil {

    /**
     * Gets random uuid without dash.
     *
     * @return random uuid without dash
     */
    @NonNull
    public static String randomUUIDWithoutDash() {
        return org.apache.commons.lang3.StringUtils.remove(UUID.randomUUID().toString(), '-');
    }

    /**
     * markdown 扩展设置
     */
    private static final MutableDataSet options = new MutableDataSet();

    static {
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Collections.singletonList(TablesExtension.create()));
    }

    /**
     * markdown 解析器
     */
    private static final Parser PARSER = Parser.builder(options).build();

    /**
     * markdown html 解析器
     */
    private static final HtmlRenderer HTML_RENDER = HtmlRenderer.builder(options).build();

    /**
     * 禁止实例化
     */
    private DiceUtil() {
        throw new TipException("Constructor not allow");
    }

    /**
     * 获取session
     *
     * @return {@link HttpSession}
     */
    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    /**
     * 获取request
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获取域名
     *
     * @return 域名字符串
     */
    public static String getDomain() {
        StringBuffer url = getRequest().getRequestURL();
        return url.delete(url.length() - getRequest().getRequestURI().length(), url.length()).append("/").toString();
    }

    /**
     * 获取ip
     *
     * @return 访问ip
     */
    public static String getIp() {
        String unknown = "unknown";
        // nginx反向代理IP
        String ip = getRequest().getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = getRequest().getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = getRequest().getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = getRequest().getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = getRequest().getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取agent
     *
     * @return User-Agent信息
     */
    public static String getAgent() {
        return getRequest().getHeader(HttpHeaders.USER_AGENT);

    }

    /**
     * 获取字符串md5值(加盐)
     *
     * @param str 字符串
     * @return 加密的字符串
     */
    public static String getMd5(String str) {
        String base = str + DiceConsts.MD5_SLAT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 获取文章预览
     *
     * @return 截取的预览字符串
     */
    public static String getSummary(String content) {
        int index = DiceUtil.ignoreCaseIndexOf(content, DiceConsts.PREVIEW_FLAG);
        if (-1 == index) {
            index = content.length() > DiceConsts.MAX_PREVIEW_COUNT ? DiceConsts.MAX_PREVIEW_COUNT : content.length();
        }
        return content.substring(0, index);
    }


    /**
     * markdown转html
     *
     * @param md markdown字符串
     * @return html字符串
     */
    public static String mdToHtml(String md) {
        if (StringUtils.isEmpty(md)) {
            return "";
        }

        Node document = PARSER.parse(md);
        return HTML_RENDER.render(document);
    }

    /**
     * 根据条件转换markdown内容
     *
     * @param content   markdown内容
     * @param isSummary 是否为摘要
     * @param isHtml    是否为 html 格式
     */
    public static String contentTransform(String content, boolean isSummary, boolean isHtml) {
        if (isSummary || isHtml) {
            if (isSummary) {
                content = DiceUtil.getSummary(content);
            }
            if (isHtml) {
                content = DiceUtil.mdToHtml(content);
            }
        }
        return content;
    }

    /**
     * 忽略大小写的indexOf
     *
     * @param str  被匹配的字符串
     * @param flag 匹配字符串
     * @return 所在的位置, 没有匹配返回-1
     */
    public static int ignoreCaseIndexOf(String str, String flag) {
        str = str.toUpperCase();
        flag = flag.toUpperCase();
        return str.indexOf(flag);
    }


    /**
     * 转换String到对应数据类型
     *
     * @param value String值
     * @param type  数据类型
     * @param <T>   T
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertStringToType(String value, Class type) {
        if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return (T) Boolean.valueOf(value);
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return (T) Integer.valueOf(value);
        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return (T) Byte.valueOf(value);
        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return (T) Short.valueOf(value);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return (T) Long.valueOf(value);
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return (T) Float.valueOf(value);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return (T) Double.valueOf(value);
        } else if (type.equals(Character.class) || type.equals(char.class)) {
            return (T) Character.valueOf(value.charAt(0));
        }

        return (T) value;
    }

    /**
     * 获取项目保存目录
     *
     * @return 项目目录文件
     */
    public static Path getFameDir() {
        Path dir = Paths.get(DiceConsts.USER_HOME, DiceConsts.DICE_HOME);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                throw new TipException(e);
            }
        }
        return dir;
    }

    /**
     * 返回文件后缀
     *
     * @param fileName 文件名
     * @return 文件后缀
     */
    public static String getFileSuffix(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return fileName.substring(index + 1);
    }

    /**
     * 获取文件文件名,不包括后缀
     *
     * @return 文件名
     */
    public static String getFileBaseName(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return fileName;
        }
        return fileName.substring(0, index);
    }

    /**
     * 压缩图片
     *
     * @param source       源文件
     * @param target       目标文件
     * @param imageQuality 压缩图片质量
     */
    public static void compressImage(File source, File target, float imageQuality) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ImageWriter imageWriter = null;
        ImageOutputStream imageOutputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            // Create the buffered image
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Get image writers
            Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");

            if (!imageWriters.hasNext()) {
                throw new IllegalStateException("Writers Not Found!!");
            }

            imageWriter = imageWriters.next();
            imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);

            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

            // Set the compress quality metrics
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(imageQuality);

            // Created image
            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
        } catch (IOException e) {
            throw new TipException(e);
        } finally {
            // close all streams
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (imageOutputStream != null) {
                    imageOutputStream.close();
                }
                if (imageWriter != null) {
                    imageWriter.dispose();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Gets machine IP address.
     *
     * @return current machine IP address.
     */
    public static String getMachineIP() {
        InetAddress machineAddress;
        try {
            machineAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            machineAddress = InetAddress.getLoopbackAddress();
        }
        return machineAddress.getHostAddress();
    }
}
