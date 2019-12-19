package com.bihell.dice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.dto.Archive;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.ArticleMapper;
import com.bihell.dice.mapper.CommentMapper;
import com.bihell.dice.model.domain.Comment;
import com.bihell.dice.service.ArticleService;
import com.bihell.dice.service.MetaService;
import com.bihell.dice.utils.DiceConsts;
import com.bihell.dice.utils.DiceUtil;
import com.bihell.dice.utils.Types;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
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
public class ArticleServiceImpl implements ArticleService {

    static final String ARTICLE_CACHE_NAME = "articles";

    private final ArticleMapper articleMapper;

    private final MetaService metasService;

    private final CommentMapper commentsMapper;

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
                .eq(Article::getStatus, Types.PUBLISH)
                .eq(Article::getType, Types.POST)
                .orderByDesc(Article::getPriority,Article::getCreated);
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
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'front_article['+#id+']'")
    public Article getFrontArticle(Integer id) {
        Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .eq(Article::getStatus, Types.PUBLISH)
                .eq(Article::getType, Types.POST));
        String content = DiceUtil.contentTransform(article.getContent(), false, true);
        article.setContent(content);
        return article;
    }

    /**
     * 分页查询后端文章
     *
     * @param current 当前页面
     * @param limit   每页数量
     * @param query   查询条件
     * @return Page<Article>
     */
    @Override
    public IPage<Article> getAdminArticles(Integer current, Integer limit, Article query) {
        Page<Article> page = new Page<>(current, limit);
        LambdaQueryWrapper<Article> wrapper = new QueryWrapper<Article>().lambda()
                .select(Article.class, info -> !"content".equals(info.getColumn()))
                .eq(Article::getType, Types.POST)
                .ne(Article::getStatus, Types.DELETE)
                .eq(!StringUtils.isEmpty(query.getStatus()), Article::getStatus, query.getStatus())
                .eq(!StringUtils.isEmpty(query.getPriority()), Article::getPriority, query.getPriority())
                .like(!StringUtils.isEmpty(query.getTitle()), Article::getTitle, query.getTitle())
                .like(!StringUtils.isEmpty(query.getTags()), Article::getTags, query.getTags())
                .like(!StringUtils.isEmpty(query.getCategory()), Article::getCategory, query.getCategory())
                .like(!StringUtils.isEmpty(query.getContent()), Article::getContent, query.getContent())
                .orderByDesc(Article::getCreated);

        return articleMapper.selectPage(page, wrapper);
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
        if (null == article) {
            throw new TipException("文章对象为空");
        }
        if (StringUtils.isEmpty(article.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (article.getTitle().length() > DiceConsts.MAX_TITLE_COUNT) {
            throw new TipException("文章标题字数不能超过" + DiceConsts.MAX_TITLE_COUNT);
        }

        if (StringUtils.isEmpty(article.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        if (article.getContent().length() > DiceConsts.MAX_CONTENT_COUNT) {
            throw new TipException("文章内容字数不能超过" + DiceConsts.MAX_CONTENT_COUNT);
        }
        if (null == article.getAuthorId()) {
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
        metasService.saveOrRemoveMetas(article.getCategory(), Types.CATEGORY, id);
        metasService.saveOrRemoveMetas(article.getTags(), Types.TAG, id);
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
        Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .eq(Article::getType, Types.POST));
        if (null == article) {
            throw new TipException("没有id为" + id + "的文章");
        }

        int rows = articleMapper.update(null, new UpdateWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .set(Article::getStatus, Types.DELETE));

        if (rows > 0) {
            log.info("删除文章: {}", article);

            // 删除文章下的评论

            int commentsResult = commentsMapper.delete(new QueryWrapper<Comment>().lambda().eq(Comment::getArticleId, id));
            log.info("删除对应的评论,数量: {}", commentsResult);

            // 传空的属性，则移除该文章关联的属性
            metasService.saveOrRemoveMetas("", Types.CATEGORY, article.getId());
            metasService.saveOrRemoveMetas("", Types.TAG, article.getId());
            return true;
        }
        return false;
    }

    /**
     * 文章数量
     *
     * @return Integer
     */

    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'article_count'")
    public Integer count() {

        return articleMapper.selectCount(new QueryWrapper<Article>().lambda()
                .eq(Article::getType, Types.POST)
                .ne(Article::getStatus, Types.DELETE));
    }

    /**
     * 获取归档信息
     *
     * @return List<Archive>
     */
    @Override
    // todo  这里用了 redis 之后缓存序列化有问题，暂时注释掉
    //@Cacheable(value = ARTICLE_CACHE_NAME, key = "'archives'")
    public List<Archive> getArchives() {
        List<Article> articles = new Article().selectList(new QueryWrapper<Article>().lambda()
                .eq(Article::getStatus, Types.PUBLISH)
                .eq(Article::getType, Types.POST)
                .orderByDesc(Article::getCreated));
        List<Archive> archives = new ArrayList<>();
        String current = "";
        for (Article article : articles) {
            // 清空文章内容
            article.setContent("");
            Calendar cal = Calendar.getInstance();
            cal.setTime(article.getCreated());
            String dateStr = String.valueOf(cal.get(Calendar.YEAR));
            if (dateStr.equals(current)) {
                Archive arc = archives.get(archives.size() - 1);
                arc.getArticles().add(article);
                arc.setCount(arc.getArticles().size());
            } else {
                current = dateStr;
                Archive arc = new Archive();
                arc.setDateStr(dateStr);
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
                .eq(Article::getStatus, Types.PUBLISH)
                .eq(Article::getType, Types.PAGE));
        String content = DiceUtil.contentTransform(article.getContent(), false, true);
        article.setContent(content);
        return article;
    }

    /**
     * 分页查询后端自定义页面
     *
     * @param current 当前页面
     * @param limit   每页数量
     * @return Page<Article>
     */
    @Override
    public IPage<Article> getAdminPages(Integer current, Integer limit) {

        Page<Article> page = new Page<>(current, limit);

        LambdaQueryWrapper<Article> wrapper = new QueryWrapper<Article>().lambda()
                .select(Article.class, info -> !"content".equals(info.getColumn()))
                .eq(Article::getType, Types.PAGE)
                .ne(Article::getStatus, Types.DELETE);

        return articleMapper.selectPage(page, wrapper);
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
        if (null == page.getAuthorId()) {
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
     * 根据id删除自定义页面
     *
     * @param id 页面id
     * @return boolean
     */
    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean deletePage(Integer id) {

        Article page = new Article().selectOne(new QueryWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .eq(Article::getType, Types.PAGE));
        if (null == page) {
            throw new TipException("没有id为" + id + "的自定义页面");
        }
        page.setStatus(Types.DELETE);
        return page.updateById();
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
                .ne(Article::getStatus, Types.DELETE)
                .orderByDesc(Article::getPriority, Article::getId));
    }
}
