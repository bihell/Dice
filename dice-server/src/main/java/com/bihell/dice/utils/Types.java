package com.bihell.dice.utils;

/**
 * 通用的Type类型
 *
 * @author bihell
 * @since 2017/8/27 22:06
 */
public interface Types {
    /**
     * 文章状态:公开
     */
    String PUBLISH = "publish";
    /**
     * 文章状态:草稿
     */
    String DRAFT = "draft";
    /**
     * 文章状态:删除
     */
    String DELETE = "delete";

    /**
     * 属性分类:归档
     */
    String CATEGORY = "category";
    /**
     * 属性分类:标签
     */
    String TAG = "tag";
    /**
     * 属性分类:代码段标签
     */
    String SNIPPET_TAG = "snippet_tag";
    /**
     * 文章类型:博客
     */
    String POST = "post";
    /**
     * 文章类型:自定义页面
     */
    String PAGE = "page";
    /**
     * 文章类型:代码段
     */
    String SNIPPET = "snippet";
    /**
     * 评论状态:正常
     */
    Integer COMMENT_STATUS_NORMAL = 0;

    /**
     * 评论状态:删除
     */
    Integer COMMENT_STATUS_DELETE = -1;

    String AGREE = "agree";
    String DISAGREE = "disagree";

    String LOG_ACTION_DELETE = "删除";
    String LOG_ACTION_SEND_EMAIL = "发送邮件";

    String LOG_MESSAGE_DELETE_ARTICLE = "删除文章";
    String LOG_MESSAGE_DELETE_PAGE = "删除自定义页面";
    String LOG_MESSAGE_SEND_EMAIL_SUCCESS = "发送邮件成功";
    String LOG_MESSAGE_SEND_EMAIL_FAIL = "发送邮件失败";

    String LOG_TYPE_VISIT = "visit";
    String LOG_TYPE_OPERATE = "operate";
    String LOG_TYPE_EMAIL = "email";


}
