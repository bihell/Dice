package com.bihell.dice.utils;

import java.io.File;
import java.util.Map;

/**
 * 常量工具类
 *
 * @author bihell
 * @since 2017/7/9 22:21
 */
public interface DiceConsts {

    /**
     * Access token cache prefix.
     */
    String TOKEN_ACCESS_CACHE_PREFIX = "dice.admin.access.token.";

    /**
     * Refresh token cache prefix.
     */
    String TOKEN_REFRESH_CACHE_PREFIX = "dice.admin.refresh.token.";

    String ACCESS_TOKEN_CACHE_PREFIX = "dice.admin.access_token.";

    String REFRESH_TOKEN_CACHE_PREFIX = "dice.admin.refresh_token.";

    /**
     * Expired seconds.
     */
    long ACCESS_TOKEN_EXPIRED_SECONDS = 24 * 3600;

    long REFRESH_TOKEN_EXPIRED_DAYS = 30;

    /**
     * Admin token param name.
     */
    String ADMIN_TOKEN_QUERY_NAME = "admin_token";

    /**
     * 登陆用户session key
     */
    String USER_SESSION_KEY = "login_user";

    /**
     * md5加密盐值
     */
    String MD5_SLAT = "riopwhjrv123bnopiw234q2ec";

    /**
     * 默认分页大小
     */
    String PAGE_SIZE = "13";

    /**
     * 文章标题最大字数
     */
    Integer MAX_TITLE_COUNT = 255;

    /**
     * 文章内容最大字数
     */
    Integer MAX_CONTENT_COUNT = 200000;

    /**
     * 默认预览字数
     */
    Integer MAX_PREVIEW_COUNT = 255;

    /**
     * 默认预览标记
     */
    String PREVIEW_FLAG = "<!--read more-->";

    /**
     * 评论最大字数
     */
    Integer MAX_COMMENT_CONTENT_COUNT = 1000;

    /**
     * 评论名称最大字数
     */
    Integer MAX_COMMENT_NAME_COUNT = 255;

    /**
     * 评论邮件最大字数
     */
    Integer MAX_COMMENT_EMAIL_COUNT = 255;

    /**
     * 评论网址最大字数
     */
    Integer MAX_COMMENT_WEBSITE_COUNT = 255;


    /**
     * 文章缓存key
     */
    String CACHE_ARTICLE_HITS = "cache_article_hits";

    /**
     * 文章点击量缓存数
     */
    Integer CACHE_ARTICLE_HITS_SAVE = 3;


    /**
     * 发送邮件的标题
     */
    String EMAIL_TEMPLATE_DEFAULT_SUBJECT = "来自BIHell博客网站发送的邮件";

    /**
     * 代码段描述最大字数
     */
    Integer MAX_SNIPPET_DESCRIPTION_COUNT = 200000;

    /**
     * 用户目录
     */
    String USER_HOME = System.getProperties().getProperty("user.home") + File.separator;

    /**
     * Dice保存目录
     */
    String DICE_HOME = ".dice" + File.separator;

    /**
     * 上传文件的路径
     */
    String UPLOAD_DIR = "upload" + File.separator;

    /**
     * 媒体文件夹路径
     */
    String MEDIA_DIR = UPLOAD_DIR + "media" + File.separator;

    /**
     * 媒体缩略图后缀
     */
    String MEDIA_THUMBNAIL_SUFFIX = "_thumbnail";

    /**
     * 获取发送给管理员的邮件内容
     *
     * @param params 填充的参数
     * @return 邮件内容
     */
    static String getEmailTemplateAdminContent(Map<String, String> params) {
        String emptyString = "";
        String websiteName = params.getOrDefault("websiteName", emptyString);
        String name = params.getOrDefault("name", emptyString);
        String content = params.getOrDefault("content", emptyString);
        String website = params.getOrDefault("website", emptyString);
        String articleId = params.getOrDefault("articleId", emptyString);

        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>" + websiteName + " Email</title>" +
                "</head>" +
                "<body>" +
                "<h3>" + websiteName + "有新的评论回复</h3>" +
                "<p>来自" + name + "的评论：" + content + "</p>" +
                "<br>" +
                "<a href=\"" + website + "article/" + articleId + "\">查看详情</a>" +
                "</body>" +
                "</html>";
    }

    static String getEmailTemplateUserContent(Map<String, String> params) {
        String emptyString = "";
        String websiteName = params.getOrDefault("websiteName", emptyString);
        String name = params.getOrDefault("name", emptyString);
        String content = params.getOrDefault("content", emptyString);
        String website = params.getOrDefault("website", emptyString);
        String articleId = params.getOrDefault("articleId", emptyString);

        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>" + websiteName + " Email</title>" +
                "</head>" +
                "<body>" +
                "<h3>你在" + websiteName + "的评论有人回复了,快去查看吧!</h3>" +
                "<p>来自" + name + "的评论：" + content + "</p>" +
                "<br>" +
                "<a href=\"" + website + "article/" + articleId + "\">查看详情</a>" +
                "</body>" +
                "</html>";
    }
}
