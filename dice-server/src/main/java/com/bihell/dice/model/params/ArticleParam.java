package com.bihell.dice.model.params;

import com.bihell.dice.model.blog.Article;

import static com.bihell.dice.utils.DateUtil.convertToLocalDateTimeVisLong;


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
        super.setCreateTime(convertToLocalDateTimeVisLong(createTime));
    }

    /**
     * 修改时间
     *
     * @param modifyTime
     */
    public void setModifyTime(Long modifyTime) {
        super.setUpdateTime(convertToLocalDateTimeVisLong(modifyTime));
    }
}
