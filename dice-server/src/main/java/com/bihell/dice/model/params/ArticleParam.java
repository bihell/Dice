package com.bihell.dice.model.params;

import com.bihell.dice.model.domain.Article;

/**
 * @author haseochen
 */
public class ArticleParam extends Article {

    /**
     * 内容生成时间
     *
     * @param created
     */
    public Article setCreated(Long created) {
        return super.setCreated(new java.util.Date(created));
    }

    /**
     * 内容修改时间
     *
     * @param modified
     */
    public Article setModified(Long modified) {
        return super.setModified(new java.util.Date(modified));
    }
}
