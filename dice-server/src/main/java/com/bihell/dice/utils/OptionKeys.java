package com.bihell.dice.utils;

import java.util.Arrays;
import java.util.List;

/**
 * SysOption 的key值
 *
 * @author bihell
 * @since 2019-05-21 00:04
 */
public interface OptionKeys {

    /**
     * 系统是否初始化
     */
    String DICE_INIT = "dice_init";

    /**
     * 博客名
     */
    String BLOG_NAME = "blog_name";

    /**
     * 博客网址
     */
    String BLOG_WEBSITE = "blog_website";

    /**
     * 博客页脚
     */
    String BLOG_FOOTER = "blog_footer";

    /**
     * SEO title
     */
    String META_TITLE = "meta_title";

    /**
     * SEO description
     */
    String META_DESCRIPTION = "meta_description";

    /**
     * SEO keywords
     */
    String META_KEYWORDS = "meta_keywords";


    /**
     * google 站长验证
     */
    String GOOGLE_SITE_VERIFICATION = "google_site_verification";

    /**
     * baidu 站长认证
     */
    String BAIDU_SITE_VERIFICATION = "baidu-site-verification";

    /**
     * google 站点分析
     */
    String GOOGLE_ANALYTICS = "google_analytics";

    /**
     * 是否开启邮件提醒
     */
    String IS_EMAIL = "is_email";

    /**
     * 邮箱host
     */
    String EMAIL_HOST = "email_host";

    /**
     * 邮箱port
     */
    String EMAIL_PORT = "email_port";

    /**
     * 邮箱
     */
    String EMAIL_USERNAME = "email_username";

    /**
     * 邮箱密码
     */
    String EMAIL_PASSWORD = "email_password";

    /**
     * 邮件标题
     */
    String EMAIL_SUBJECT = "email_subject";


    /**
     * 传给前端的Option的keys
     */
    List<String> FRONT_OPTION_KEYS = Arrays.asList(
            BLOG_NAME,
            BLOG_WEBSITE,
            BLOG_FOOTER,
            META_TITLE,
            META_DESCRIPTION,
            META_KEYWORDS,
            GOOGLE_SITE_VERIFICATION,
            BAIDU_SITE_VERIFICATION,
            GOOGLE_ANALYTICS);
}
