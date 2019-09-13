package com.bihell.dice.service;

import com.bihell.dice.model.dto.ArticleInfoDto;
import com.bihell.dice.model.dto.Snippet;

import java.util.List;

/**
 * 代码段 Service 接口
 * @author haseochen
 */
public interface SnippetService {


    /**
     * 保存或更新代码段
     *
     * @param snippet 代码段 entity
     * @return Integer
     */
    Integer saveSnippet(Snippet snippet);

    /**
     * 删除代码段
     * @param snippetId 代码段 id
     * @return Boolean
     */
    Boolean deleteSnippet(Integer snippetId);

    /**
     * 根据标签 id 获得对应的代码段
     * @param metaId 标签 id
     * @return List<ArticleInfoDto>
     */
    List<ArticleInfoDto> getSnippetByMeta(Integer metaId);

    /**
     * 根据id 获取代码段
     * @param snippetId 代码段 Id
     * @return Snippet
     */
    Snippet getSnippetById(Integer snippetId);

}
