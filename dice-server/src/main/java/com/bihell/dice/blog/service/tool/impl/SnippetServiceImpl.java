package com.bihell.dice.blog.service.tool.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.blog.mapper.blogs.ArticleMapper;
import com.bihell.dice.blog.mapper.tool.SnippetFileMapper;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.model.dto.ArticleInfoDto;
import com.bihell.dice.blog.model.dto.Snippet;
import com.bihell.dice.blog.model.tool.SnippetFile;
import com.bihell.dice.blog.enums.PostStatusEnum;
import com.bihell.dice.blog.service.blog.MetaService;
import com.bihell.dice.blog.service.tool.SnippetService;
import com.bihell.dice.blog.utils.Types;
import com.bihell.dice.commons.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SnippetServiceImpl implements SnippetService {

    private final ArticleMapper articleMapper;

    private final MetaService metasService;

    private final SnippetFileMapper snippetFilesMapper;

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
        if (StringUtils.isEmpty(snippet.getTitle())) {
            throw new BusinessException("代码段标题不能为空");
        } else {
            article.setTitle(snippet.getTitle());
        }
        article.setContent(snippet.getDescription());
        if (StringUtils.isEmpty(snippet.getLabel())) {
            throw new BusinessException("代码段标签不能为空");
        } else {
            article.setTags(snippet.getLabel());
        }
        article.setStatus(PostStatusEnum.PUBLISHED);
        if (null != article.getId()) {
            // 更新代码段
            article.updateById();
            // 更新代码段文件
            snippet.getSnippetFiles().forEach(snippetFile -> {
                if (StringUtils.isEmpty(snippetFile.getTitle())) {
                    throw new BusinessException("代码段文件标题不能为空");
                }
                if (StringUtils.isEmpty(snippetFile.getContent())) {
                    throw new BusinessException("代码段文件内容不能为空");
                }
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
                if (StringUtils.isEmpty(snippetFile.getTitle())) {
                    throw new BusinessException("代码段文件标题不能为空");
                }
                if (StringUtils.isEmpty(snippetFile.getContent())) {
                    throw new BusinessException("代码段文件内容不能为空");
                }
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
            throw new BusinessException("没有id为" + snippetId + "的代码段");
        }

        if (article.deleteById()) {
            log.info("删除代码段: {}", article);

            // 删除代码段文件
            int commentsResult = snippetFilesMapper.delete(new QueryWrapper<SnippetFile>().lambda().eq(SnippetFile::getSnippetId, snippetId));
            log.info("删除对应的代码段,数量: {}", commentsResult);

            // TODO: 2023/6/26 这里删除代码段标签有问题待修改
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
