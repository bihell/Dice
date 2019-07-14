package com.bihell.dice.service;

import com.bihell.dice.model.domain.Comment;

/**
 * 发送邮件 Service 接口
 *
 * @author bihell
 * @since 2018/4/9 15:51
 */
public interface EmailService {

    /**
     * 发送邮件给管理员
     *
     * @param comment 回复的Comment信息
     */
    void sendEmailToAdmin(Comment comment);

    /**
     * 发送邮件给被评论的用户
     *
     * @param comment   评论的Comment信息
     * @param replyEmail 被评论人邮箱
     */
    void sendEmailToUser(Comment comment, String replyEmail);
}
