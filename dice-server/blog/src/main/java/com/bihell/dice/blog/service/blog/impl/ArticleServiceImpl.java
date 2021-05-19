package com.bihell.dice.blog.service.blog.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.blog.mapper.blogs.ArticleMapper;
import com.bihell.dice.blog.mapper.blogs.CommentMapper;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.model.blog.Comment;
import com.bihell.dice.blog.model.dto.Archive;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.shiro.cache.LoginRedisService;
import com.bihell.dice.framework.util.LoginUtil;
import com.bihell.dice.blog.enums.PostStatusEnum;
import com.bihell.dice.framework.common.exception.TipException;
import com.bihell.dice.blog.service.blog.ArticleService;
import com.bihell.dice.blog.service.blog.MetaService;
import com.bihell.dice.framework.common.service.RedisService;
import com.bihell.dice.config.constant.DiceConsts;
import com.bihell.dice.framework.util.DiceUtil;
import com.bihell.dice.blog.utils.Types;
import com.bihell.dice.blog.param.ArticlePageParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章 Service 实现类
 *
 * @author bihell
 * @since 2017/8/21 22:02
 */
@Slf4j
@Service("articlesService")
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    static final String ARTICLE_CACHE_NAME = "articles";

    private final ArticleMapper articleMapper;
    private final MetaService metasService;
    private final CommentMapper commentsMapper;
    private final RedisService redisService;
    private final LoginRedisService loginRedisService;

    /**
     * 分页查询前端文章
     *
     * @param current 当前页面
     * @param limit   每页数量
     * @return Page<Article>
     */
    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'font_articles['+#current+':'+#limit+']'")
    public IPage<Article> getFrontArticles(Integer current, Integer limit) {

        Page<Article> page = new Page<>(current, limit);
        LambdaQueryWrapper<Article> wrapper = new QueryWrapper<Article>().lambda()
                .eq(Article::getStatus, PostStatusEnum.PUBLISHED)
                .eq(Article::getType, Types.POST)
                .orderByDesc(Article::getPriority, Article::getCreateTime);
        IPage<Article> result = articleMapper.selectPage(page, wrapper);

        result.getRecords().forEach(article -> {
            String content = DiceUtil.contentTransform(article.getContent(), true, true);
            article.setContent(content);
        });

        return result;
    }

    /**
     * 根据id获取前端文章
     *
     * @param id 文章id
     * @return Article
     */
    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'front_article['+#id+']['+#token+']'")
    public Article getFrontArticle(Integer id, String token) {
        if (StringUtils.isEmpty(token)) {
            Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                    .eq(Article::getId, id)
                    .eq(Article::getStatus, PostStatusEnum.PUBLISHED)
                    .eq(Article::getType, Types.POST));
            if (article != null) {
                String content = DiceUtil.contentTransform(article.getContent(), false, true);
                article.setContent(content);
            }
            return article;
        } else {
            Long userId = LoginUtil.getUserId(token);
            Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                    .eq(Article::getId, id)
                    .eq(Article::getType, Types.POST)
                    .eq(Article::getCreator, userId));
            if (article != null) {
                String content = DiceUtil.contentTransform(article.getContent(), false, true);
                article.setContent(content);
            }
            return article;
        }
    }

    @Override
    public Paging<Article> getArticlePageList(ArticlePageParam articlePageParam) throws Exception {
        Page<Article> page = new PageInfo<>(articlePageParam, OrderItem.desc(getLambdaColumn(Article::getUpdateTime)));
        LambdaQueryWrapper<Article> wrapper = new QueryWrapper<Article>().lambda()
                .select(Article.class, info -> !"content".equals(info.getColumn()))
                .eq(Article::getType, Types.POST)
                .eq(!StringUtils.isEmpty(articlePageParam.getStatus()), Article::getStatus, articlePageParam.getStatus())
                .eq(!StringUtils.isEmpty(articlePageParam.getPriority()), Article::getPriority, articlePageParam.getPriority())
                .like(!StringUtils.isEmpty(articlePageParam.getTitle()), Article::getTitle, articlePageParam.getTitle())
                .like(!StringUtils.isEmpty(articlePageParam.getContent()), Article::getContent, articlePageParam.getContent());
        IPage<Article> iPage = articleMapper.selectPage(page, wrapper);
        return new Paging<>(iPage);
    }

    /**
     * 根据id获取后端文章
     *
     * @param id 文章id
     * @return Article
     */
    @Override
    public Article getAdminArticle(Integer id) {
        Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .eq(Article::getType, Types.POST));
        String content = DiceUtil.contentTransform(article.getContent(), false, false);
        article.setContent(content);
        return article;
    }

    /**
     * 保存或更新文章
     *
     * @param article 文章entity
     * @return Integer
     */

    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Integer saveArticle(Article article) {
        if (null == article.getCreator()) {
            throw new TipException("请先登陆后发布文章");
        }

        if (null != article.getId()) {
            article.updateById();
        } else {
            article.setType(Types.POST);
            article.insert();
        }

        Integer id = article.getId();

        // 存储分类和标签
        if (article.getCategory() != null) {
            metasService.saveOrRemoveMetas(article.getCategory(), Types.CATEGORY, id);
        }
        if (article.getTags() != null) {
            metasService.saveOrRemoveMetas(article.getTags(), Types.TAG, id);
        }

        return id;
    }


    /**
     * 更新文章
     *
     * @param article 文章entity
     * @return boolean
     */
    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean updateArticle(Article article) {
        if (null == article) {
            throw new TipException("文章不能为空");
        }
        return article.updateById();
    }


    /**
     * 根据id删除文章
     *
     * @param id 文章id
     * @return boolean
     */
    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean deleteArticle(Integer id) {

        if (articleMapper.deleteById(id) > 0) {
            log.info("删除文章: {}", id);

            // 删除文章下的评论

            int commentsResult = commentsMapper.delete(new QueryWrapper<Comment>().lambda().eq(Comment::getArticleId, id));
            log.info("删除对应的评论,数量: {}", commentsResult);

            // 传空的属性，则移除该文章关联的属性
            metasService.saveOrRemoveMetas("", Types.CATEGORY, id);
            metasService.saveOrRemoveMetas("", Types.TAG, id);
            return true;
        }
        return false;
    }

    /**
     * 获取归档信息
     *
     * @return List<Archive>
     */
    @Override
    // todo  这里用了 redis 之后缓存序列化有问题
    //@Cacheable(value = ARTICLE_CACHE_NAME, key = "'archives'")
    public List<Archive> getArchives() {
        List<Article> articles = new Article().selectList(new QueryWrapper<Article>().lambda()
                .eq(Article::getStatus, PostStatusEnum.PUBLISHED)
                .eq(Article::getType, Types.POST)
                .orderByDesc(Article::getCreateTime));
        List<Archive> archives = new ArrayList<>();
        String current = "";
        for (Article article : articles) {
            // 清空文章内容
            article.setContent("");
            String year = String.valueOf(article.getCreateTime().getYear());
            if (year.equals(current)) {
                Archive arc = archives.get(archives.size() - 1);
                arc.getArticles().add(article);
                arc.setCount(arc.getArticles().size());
            } else {
                current = year;
                Archive arc = new Archive();
                arc.setDateStr(year);
                arc.setCount(1);
                List<Article> arts = new ArrayList<>();
                arts.add(article);
                arc.setArticles(arts);
                archives.add(arc);
            }
        }
        return archives;
    }

    /**
     * 根据title获取前端自定义页面
     *
     * @param id 页面标题
     * @return Article
     */
    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'front_page['+#id+']'")
    public Article getFrontPage(Integer id) {
        Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .eq(Article::getStatus, PostStatusEnum.PUBLISHED)
                .eq(Article::getType, Types.PAGE));
        String content = DiceUtil.contentTransform(article.getContent(), false, true);
        article.setContent(content);
        return article;
    }

    /**
     * 分页查询后端自定义页面
     * @param articlePageParam
     * @return
     */
    @Override
    public Paging<Article> getAdminPages(ArticlePageParam articlePageParam) {

        Page<Article> page = new PageInfo<>(articlePageParam, OrderItem.desc(getLambdaColumn(Article::getUpdateTime)));

        LambdaQueryWrapper<Article> wrapper = new QueryWrapper<Article>().lambda()
                .select(Article.class, info -> !"content".equals(info.getColumn()))
                .eq(Article::getType, Types.PAGE);
        IPage<Article> iPage = articleMapper.selectPage(page, wrapper);
        return new Paging<>(iPage);
    }

    /**
     * 根据id获取后端页面
     *
     * @param id 文章id
     * @return Article
     */
    @Override
    public Article getAdminPage(Integer id) {
        Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .eq(Article::getType, Types.PAGE));
        String content = DiceUtil.contentTransform(article.getContent(), false, false);
        article.setContent(content);
        return article;
    }

    /**
     * 保存或更新自定义页面
     *
     * @param page 页面entity
     * @return Integer
     */
    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Integer savePage(Article page) {
        if (null == page) {
            throw new TipException("自定义页面对象为空");
        }
        if (StringUtils.isEmpty(page.getTitle())) {
            throw new TipException("自定义页面标题不能为空");
        }
        if (page.getTitle().length() > DiceConsts.MAX_TITLE_COUNT) {
            throw new TipException("自定义页面标题字数不能超过" + DiceConsts.MAX_TITLE_COUNT);
        }

        if (StringUtils.isEmpty(page.getContent())) {
            throw new TipException("自定义页面内容不能为空");
        }
        if (page.getContent().length() > DiceConsts.MAX_CONTENT_COUNT) {
            throw new TipException("自定义页面容字数不能超过" + DiceConsts.MAX_CONTENT_COUNT);
        }
        if (null == page.getCreator()) {
            throw new TipException("请先登陆");
        }
        if (null != page.getId()) {
            page.updateById();
        } else {
            page.setType(Types.PAGE);
            page.insert();
        }

        return page.getId();
    }

    /**
     * 保存或更新代码段
     *
     * @param snippet 代码段entity
     * @return Integer
     */
    @Override
    public Integer saveSnippet(Article snippet) {
        if (null == snippet) {
            throw new TipException("自定义代码段对象为空");
        }
        if (StringUtils.isEmpty(snippet.getTitle())) {
            throw new TipException("自定义代码段标题不能为空");
        }
        if (snippet.getTitle().length() > DiceConsts.MAX_TITLE_COUNT) {
            throw new TipException("自定义代码段标题字数不能超过" + DiceConsts.MAX_TITLE_COUNT);
        }

        if (StringUtils.isEmpty(snippet.getContent())) {
            throw new TipException("自定义代码段内容不能为空");
        }
        if (snippet.getContent().length() > DiceConsts.MAX_CONTENT_COUNT) {
            throw new TipException("自定义代码段容字数不能超过" + DiceConsts.MAX_CONTENT_COUNT);
        }

        if (null != snippet.getId()) {
            snippet.updateById();
        } else {
            snippet.setType(Types.SNIPPET);
            snippet.insert();
        }

        Integer id = snippet.getId();

        // 存储分类和标签
        metasService.saveOrRemoveMetas(snippet.getTags(), Types.SNIPPET_TAG, id);

        return snippet.getId();
    }

    /**
     * 获取前端自定义页面信息列表
     *
     * @return List<ArticleInfoDto>
     */
    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'front_pages'")
    public List<Article> getFrontPageList() {

        return articleMapper.selectList(new QueryWrapper<Article>().lambda()
                .select(Article.class, info -> !"content".equals(info.getColumn()))
                .eq(Article::getType, Types.PAGE)
                .orderByDesc(Article::getPriority, Article::getId));
    }
}
