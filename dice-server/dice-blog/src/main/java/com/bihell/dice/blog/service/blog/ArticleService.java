package com.bihell.dice.blog.service.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.model.dto.Archive;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.blog.param.ArticlePageParam;

import java.util.List;

/**
 * 文章 Service 接口
 *
 * @author bihell
 * @since 2017/8/21 22:01
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询前端文章
     *
     * @param current  当前页面
     * @param limit 每页数量
     * @return Page<Article>
     */
    IPage<Article> getFrontArticles(Integer current, Integer limit);

    /**
     * 根据id获取前端文章
     *
     * @param id 文章id
     * @return Article
     */
    Article getFrontArticle(Integer id,String token);

    /**
     * 分页查询文章列表
     * @param articlePageParam
     * @return
     * @throws Exception
     */
    Paging<Article> getArticlePageList(ArticlePageParam articlePageParam) throws Exception;

    /**
     * 分页查询后端自定义页面
     * @param articlePageParam
     * @return
     */
    Paging<Article> getAdminPages(ArticlePageParam articlePageParam);

    /**
     * 根据id获取后端文章
     *
     * @param id 文章id
     * @return Article
     */
    Article getAdminArticle(Integer id);

    /**
     * 保存或更新文章
     *
     * @param article 文章entity
     * @return Integer
     */
    Integer saveArticle(Article article);

    /**
     * 更新文章
     *
     * @param articles 文章entity
     * @return boolean
     */
    boolean updateArticle(Article articles);

    /**
     * 根据id删除文章
     *
     * @param id 文章id
     * @return boolean
     */
    boolean deleteArticle(Integer id);

    /**
     * 获取归档信息
     *
     * @return List<Archive>
     */
    List<Archive> getArchives();

    /**
     * 根据title获取前端自定义页面
     *
     * @param id 页面id
     * @return Article
     */
    Article getFrontPage(Integer id);

    /**
     * 根据id获取后端页面
     *
     * @param id 文章id
     * @return Article
     */
    Article getAdminPage(Integer id);

    /**
     * 保存或更新自定义页面
     *
     * @param page 页面entity
     * @return Integer
     */
    Integer savePage(Article page);

    /**
     * 保存或更新代码段
     *
     * @param snippet 代码段entity
     * @return Integer
     */
    Integer saveSnippet(Article snippet);

    /**
     * 获取前端自定义页面信息列表
     *
     * @return List<ArticleInfoDto>
     */
    List<Article> getFrontPageList();
}
