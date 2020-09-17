package com.bihell.dice.blog.model.params;

import com.bihell.dice.blog.model.blog.Article;

import static com.bihell.dice.blog.utils.DateUtil.convertToLocalDateTimeVisLong;


/**
 * @author haseochen
 */
public class ArticleParam extends Article {

    /**
     * 创建时间
     **/
    public void setCreateTime(Long createTime) {
        super.setCreateTime(convertToLocalDateTimeVisLong(createTime));
    }

    /**
     * 修改时间
     **/
    public void setUpdateTime(Long updateTime) {
        super.setUpdateTime(convertToLocalDateTimeVisLong(updateTime));
    }
}
