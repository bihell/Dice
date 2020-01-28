package com.bihell.dice.model.params;

import com.bihell.dice.model.blog.Article;

/**
 * @author haseochen
 */
public class ArticleParam extends Article {

    /**
     * 创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        super.setCreateTime(new java.util.Date(createTime));
    }

    /**
     * 修改时间
     *
         * @param modifyTime
     */
    public void setModifyTime(Long modifyTime) {
        super.setModifyTime(new java.util.Date(modifyTime));
    }
}
