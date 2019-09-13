package com.bihell.dice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.ArticleMapper;
import com.bihell.dice.mapper.SnippetFileMapper;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.domain.SnippetFile;
import com.bihell.dice.model.dto.ArticleInfoDto;
import com.bihell.dice.model.dto.Snippet;
import com.bihell.dice.service.MetaService;
import com.bihell.dice.service.SnippetService;
import com.bihell.dice.util.Types;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * 文章 Service 实现类
 *
 * @author bihell
 * @since 2017/8/21 22:02
 */
@Slf4j
@Service("snippetService")
@Transactional(rollbackFor = Throwable.class)
public class SnippetServiceImpl implements SnippetService {

    static final String ARTICLE_CACHE_NAME = "articles";

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private MetaService metasService;

    @Resource
    private SnippetFileMapper snippetFilesMapper;

    /**
     * 保存或更新文章
     *
     * @param snippet 文章entity
     * @return Integer
     */
    @Override
    public Integer saveSnippet(Snippet snippet) {
        Article article = new Article();
        if (!StringUtils.isEmpty(snippet.getId())) {
            article.setId(snippet.getId());
        }
        article.setTitle(snippet.getTitle());
        article.setContent(snippet.getDescription());
        article.setTags(snippet.getLabel());
        article.setStatus(Types.PUBLISH);
        if (null != article.getId()) {
            // 更新代码段
            article.updateById();
            // 更新代码段文件
            snippet.getSnippetFiles().forEach(snippetFile -> {
                System.out.println("aaaaa" + snippetFile.getDestroy());
                if (null != snippetFile.getDestroy() && snippetFile.getDestroy()) {
                    snippetFile.deleteById();
                } else if (null != snippetFile.getId()) {
                    snippetFile.updateById();
                } else {
                    snippetFile.setSnippetId(article.getId());
                    snippetFile.insert();
                }
            });
        } else {
            article.setType(Types.SNIPPET);
            // 插入代码段
            article.insert();
            // 保存代码段文件
            snippet.getSnippetFiles().forEach(snippetFile -> {
                snippetFile.setSnippetId(article.getId());
                snippetFile.insert();
            });
        }

        // 存储标签
        metasService.saveOrRemoveMetas(article.getTags(), Types.SNIPPET_TAG, article.getId());

        return article.getId();
    }

    /**
     * 删除代码段
     *
     * @param snippetId 代码 id
     * @return Boolean
     */
    @Override
    public Boolean deleteSnippet(Integer snippetId) {
        Article article = new Article().selectOne(new QueryWrapper<Article>().lambda()
                .eq(Article::getId, snippetId)
                .eq(Article::getType, Types.SNIPPET));
        if (null == article) {
            throw new TipException("没有id为" + snippetId + "的代码段");
        }

        int rows = articleMapper.update(null, new UpdateWrapper<Article>().lambda()
                .eq(Article::getId, snippetId)
                .set(Article::getStatus, Types.DELETE));

        if (rows > 0) {
            log.info("删除代码段: {}", article);

            // 删除代码段文件

            int commentsResult = snippetFilesMapper.delete(new QueryWrapper<SnippetFile>().lambda().eq(SnippetFile::getSnippetId, snippetId));
            log.info("删除对应的代码段,数量: {}", commentsResult);

            // 传空的属性，则移除该代码段关联的属性
            metasService.saveOrRemoveMetas("", Types.SNIPPET_TAG, article.getId());
            return true;
        }
        return false;
    }

    /**
     * 根据标签 id 获得对应的代码段
     *
     * @param metaId 标签 id
     * @return List<ArticleInfoDto>
     */
    @Override
    public List<ArticleInfoDto> getSnippetByMeta(Integer metaId) {
        return articleMapper.selectSnippetByMeta(metaId);
    }

    /**
     * 根据id 获取代码段
     *
     * @param snippetId 代码段 Id
     * @return Snippet
     */
    @Override
    public Snippet getSnippetById(Integer snippetId) {
        Article article = new Article().selectById(snippetId);
        Snippet snippet = new Snippet();
        snippet.setDescription(article.getContent());
        snippet.setId(article.getId());
        snippet.setLabel(article.getTags());
        snippet.setTitle(article.getTitle());
        snippet.setSnippetFiles(snippetFilesMapper.selectList(new QueryWrapper<SnippetFile>().lambda().eq(SnippetFile::getSnippetId, article.getId())));
        return snippet;
    }
}
