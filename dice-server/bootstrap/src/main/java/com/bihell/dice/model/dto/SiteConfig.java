package com.bihell.dice.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 网站设置 Dto
 *
 * @author bihell
 * @since 2017/10/15 21:48
 */
@Data
@Builder
@Accessors(chain = true)
public class SiteConfig {

    /**
     * 网站title
     */
    private String title;

    /**
     * 网站描述
     */
    private String description;

    /**
     * 网站keywords
     */
    private String keywords;

    /**
     * 是否设置邮箱提醒
     */
    private boolean emailSend;

    /**
     * 邮箱Host
     */
    private String emailHost;

    /**
     * 邮箱Port
     */
    private Integer emailPort;

    /**
     * 网站评论提醒邮箱
     */
    private String emailUsername;

    /**
     * 网站评论提醒邮箱密码
     */
    private String emailPassword;
}
