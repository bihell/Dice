package com.bihell.dice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.dto.Archive;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.ArticleMapper;
import com.bihell.dice.mapper.CommentMapper;
import com.bihell.dice.model.domain.Comment;
import com.bihell.dice.model.query.ArticleQuery;
import com.bihell.dice.service.ArticleService;
import com.bihell.dice.service.MetaService;
import com.bihell.dice.util.DiceConsts;
import com.bihell.dice.util.DiceUtil;
import com.bihell.dice.util.Types;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

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
public class ArticleServiceImpl implements ArticleService {

    public static final String ARTICLE_CACHE_NAME = "articles";

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private MetaService metasService;

    @Autowired
    private CommentMapper commentsMapper;

    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'font_articles['+#page+':'+#limit+']'")
    public Page<Article> getFrontArticles(Integer page, Integer limit) {
        Article record = new Article();
        record.setStatus(Types.PUBLISH);
        record.setType(Types.POST);

        Page<Article> result = PageHelper.startPage(page, limit).doSelectPage(() -> articleMapper.select(record));
        result.forEach(article -> {
            String content = DiceUtil.contentTransform(article.getContent(), true, true);
            article.setContent(content);
        });
        return result;
    }

    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'front_article['+#id+']'")
    public Article getFrontArticle(Integer id) {
        Article record = new Article();
        record.setId(id);
        record.setStatus(Types.PUBLISH);
        record.setType(Types.POST);
        Article article = articleMapper.selectOne(record);
        String content = DiceUtil.contentTransform(article.getContent(), false, true);
        article.setContent(content);
        return article;
    }

    @Override
    public Page<Article> getAdminArticles(Integer page, Integer limit, ArticleQuery query) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", Types.POST);
        criteria.andNotEqualTo("status", Types.DELETE);
        if (!StringUtils.isEmpty(query.getStatus())) {
            criteria.andEqualTo("status", query.getStatus());
        }
        if (!StringUtils.isEmpty(query.getTitle())) {
            criteria.andLike("title", "%" + query.getTitle() + "%");
        }
        if (!StringUtils.isEmpty(query.getTag())) {
            criteria.andLike("tags", "%" + query.getTag() + "%");
        }
        if (!StringUtils.isEmpty(query.getCategory())) {
            criteria.andLike("category", "%" + query.getCategory() + "%");
        }
        Page<Article> result = PageHelper.startPage(page, limit).doSelectPage(() -> articleMapper.selectByExample(example));
        //只需要文章列表，不需要内容
        result.forEach(article -> article.setContent(""));
        return result;
    }

    @Override
    public Article getAdminArticle(Integer id) {
        Article record = new Article();
        record.setId(id);
        record.setType(Types.POST);
        Article article = articleMapper.selectOne(record);
        String content = DiceUtil.contentTransform(article.getContent(), false, false);
        article.setContent(content);
        return article;
    }


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
            articleMapper.updateByPrimaryKeySelective(article);
        } else {
            article.setType(Types.POST);
            articleMapper.insertSelective(article);
        }

        Integer id = article.getId();
        //存储分类和标签
        metasService.saveOrRemoveMetas(article.getCategory(), Types.CATEGORY, id);
        metasService.saveOrRemoveMetas(article.getTags(), Types.TAG, id);
        return id;
    }

    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean updateArticle(Article article) {
        if (null == article) {
            throw new TipException("文章不能为空");
        }
        return articleMapper.updateByPrimaryKeySelective(article) > 0;
    }

    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean deleteArticle(Integer id) {
        Article record = new Article();
        record.setId(id);
        record.setType(Types.POST);
        Article article = articleMapper.selectOne(record);
        if (null == article) {
            throw new TipException("没有id为" + id + "的文章");
        }

        record.setStatus(Types.DELETE);
        if (articleMapper.updateByPrimaryKeySelective(record) > 0) {
            log.info("删除文章: {}", article);

            // 删除文章下的评论
            Example commentsExample = Example
                    .builder(Comment.class)
                    .where(Sqls.custom()
                            .andEqualTo("articleId", id))
                    .build();
            int commentsResult = commentsMapper.deleteByExample(commentsExample);
            log.info("删除对应的评论,数量: {}", commentsResult);

            // 传空的属性，则移除该文章关联的属性
            metasService.saveOrRemoveMetas("", Types.CATEGORY, article.getId());
            metasService.saveOrRemoveMetas("", Types.TAG, article.getId());
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'article_count'")
    public Integer count() {
        Example articleExample = Example
                .builder(Article.class)
                .where(Sqls.custom()
                        .andEqualTo("type",Types.POST)
                        .andNotEqualTo("status", Types.DELETE))
                .build();
        return articleMapper.selectCountByExample(articleExample);
    }

    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'archives'")
    public List<Archive> getArchives() {
        Article record = new Article();
        record.setStatus(Types.PUBLISH);
        record.setType(Types.POST);
        List<Article> articles = articleMapper.select(record);
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

    @Override
    @Cacheable(value = ARTICLE_CACHE_NAME, key = "'front_page['+#title+']'")
    public Article getFrontPage(String title) {
        Article record = new Article();
        record.setTitle(title);
        record.setStatus(Types.PUBLISH);
        record.setType(Types.PAGE);
        Article article = articleMapper.selectOne(record);
        String content = DiceUtil.contentTransform(article.getContent(), false, true);
        article.setContent(content);
        return article;
    }

    @Override
    public Page<Article> getAdminPages(Integer page, Integer limit) {
        Article record = new Article();
        record.setType(Types.PAGE);
        Example pageExample = Example
                .builder(Article.class)
                .where(Sqls.custom()
                        .andEqualTo("type", Types.PAGE)
                        .andNotEqualTo("status", Types.DELETE))
                .build();

        Page<Article> result = PageHelper.startPage(page, limit).doSelectPage(() -> articleMapper.selectByExample(pageExample));
        //只需要文章列表，不需要内容
        result.forEach(article -> article.setContent(""));
        return result;
    }

    @Override
    public Article getAdminPage(Integer id) {
        Article record = new Article();
        record.setId(id);
        record.setType(Types.PAGE);
        Article article = articleMapper.selectOne(record);
        String content = DiceUtil.contentTransform(article.getContent(), false, false);
        article.setContent(content);
        return article;
    }

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
            articleMapper.updateByPrimaryKeySelective(page);
        } else {
            page.setType(Types.PAGE);
            articleMapper.insertSelective(page);
        }

        return page.getId();
    }

    @Override
    @CacheEvict(value = ARTICLE_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean deletePage(Integer id) {
        Article record = new Article();
        record.setId(id);
        record.setType(Types.PAGE);
        Article page = articleMapper.selectOne(record);
        if (null == page) {
            throw new TipException("没有id为" + id + "的自定义页面");
        }
        record.setStatus(Types.DELETE);
        return articleMapper.updateByPrimaryKeySelective(record) > 0;
    }
}
