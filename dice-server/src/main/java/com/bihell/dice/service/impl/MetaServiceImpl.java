package com.bihell.dice.service.impl;

import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.domain.Meta;
import com.bihell.dice.model.domain.Middle;
import com.bihell.dice.model.dto.MetaDto;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.ArticleMapper;
import com.bihell.dice.mapper.MetaMapper;
import com.bihell.dice.mapper.MiddleMapper;
import com.bihell.dice.service.MetaService;
import com.bihell.dice.util.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 属性 Service 实现类
 *
 * @author bihell
 * @since 2017/8/28 23:33
 */
@Service("metasService")
@Transactional(rollbackFor = Throwable.class)
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MiddleMapper middleMapper;

    @Autowired
    private MetaMapper metaMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<MetaDto> getPublishMetaDtos(String type) {
        type = verifyType(type);
        return metaMapper.selectPublishMetaDtos(type);
    }

    @Override
    public List<MetaDto> getMetaDtos(String type) {
        type = verifyType(type);
        return metaMapper.selectMetaDtos(type);
    }

    @Override
    public boolean deleteMeta(String name, String type) {
        type = verifyType(type);
        Meta meta = metaMapper.selectOne(new Meta(name, type));
        if (null == meta) {
            throw new TipException("没有该名称的属性");
        }
        List<Middle> middles = middleMapper.select(new Middle(null, meta.getId()));
        for (Middle middle : middles) {
            Article article = articleMapper.selectByPrimaryKey(middle.getAId());
            if (null != article) {
                if (type.equals(Types.CATEGORY)) {
                    article.setCategory("");
                }
                if (type.equals(Types.TAG)) {
                    article.setTags(this.resetMeta(name, article.getTags()));
                }
                articleMapper.updateByPrimaryKey(article);
            }
        }

        middleMapper.delete(new Middle(null, meta.getId()));
        metaMapper.delete(meta);
        return true;
    }

    @Override
    public boolean saveMeta(String name, String type) {
        if (StringUtils.isEmpty(name)) {
            throw new TipException("属性名不能为空");
        }
        type = verifyType(type);
        Meta metas = new Meta();
        metas.setType(type);
        metas.setName(name);
        if (metaMapper.select(metas).size() > 0) {
            throw new TipException("该属性已经存在");
        }

        return metaMapper.insert(metas) > 0;
    }

    @Override
    public boolean updateMeta(Integer id, String name, String type) {
        if (StringUtils.isEmpty(name)) {
            throw new TipException("属性名不能为空");
        }
        type = verifyType(type);
        Meta meta = metaMapper.selectByPrimaryKey(id);
        if (null == meta) {
            throw new TipException("没有该属性");
        }

        List<Article> articles = articleMapper.selectByMeta(id);
        for (Article article : articles) {
            String metas;
            if (type.equals(Types.CATEGORY)) {
                metas = article.getCategory();
                String newMetas = metas.replace(meta.getName(), name);
                if (!newMetas.equals(metas)) {
                    article.setCategory(newMetas);
                    articleMapper.updateByPrimaryKeySelective(article);
                }
            }
            if (type.equals(Types.TAG)) {
                metas = article.getTags();
                String newMetas = metas.replace(meta.getName(), name);
                if (!newMetas.equals(metas)) {
                    article.setTags(newMetas);
                    articleMapper.updateByPrimaryKeySelective(article);
                }
            }
        }
        meta.setName(name);
        return metaMapper.updateByPrimaryKeySelective(meta) > 0;
    }

    @Override
    public boolean saveOrRemoveMetas(String names, String type, Integer articleId) {
        type = verifyType(type);
        if (null == articleId) {
            throw new TipException("关联文章id不能为空");
        }

        removeMetas(names, type, articleId);
        saveMetas(names, type, articleId);
        return true;
    }


    /**
     * 添加names新加的属性到数据库
     *
     * @param names
     * @param type
     * @param articleId
     */
    private void saveMetas(String names, String type, Integer articleId) {
        List<Meta> metas = metaMapper.selectByArticle(articleId, type);
        Set<String> metaSet = new HashSet<>();
        for (Meta meta : metas) {
            metaSet.add(meta.getName());
        }
        String[] nameArr = names.split(",");
        for (String name : nameArr) {
            if (StringUtils.isEmpty(name)) {
                continue;
            }
            if (!metaSet.contains(name)) {
                Meta newMeta = new Meta(name, type);
                Meta meta = metaMapper.selectOne(newMeta);
                if (null == meta) {
                    metaMapper.insert(newMeta);
                } else {
                    newMeta = meta;
                }
                middleMapper.insert(new Middle(articleId, newMeta.getId()));
            }
        }
    }

    /**
     * 从数据库中删除names属性中没有的
     *
     * @param names
     * @param type
     * @param articleId
     */
    private void removeMetas(String names, String type, Integer articleId) {
        String[] nameArr = names.split(",");
        Set<String> nameSet = new HashSet<>(Arrays.asList(nameArr));
        List<Meta> metas = metaMapper.selectByArticle(articleId, type);
        for (Meta meta : metas) {
            if (!nameSet.contains(meta.getName())) {
                middleMapper.delete(new Middle(articleId, meta.getId()));
            }
        }

    }


    /**
     * 验证Type是否为定义的
     *
     * @return
     */
    private String verifyType(String type) {
        switch (type) {
            case Types.CATEGORY:
                return type;
            case Types.TAG:
                return type;
            default:
                throw new TipException("传输的属性类型不合法");
        }
    }

    /**
     * 从属性字符串中去除一个属性
     *
     * @param name
     * @param metas
     * @return
     */
    private String resetMeta(String name, String metas) {
        String[] metaArr = metas.split(",");
        StringBuffer sb = new StringBuffer();
        for (String m : metaArr) {
            if (!name.equals(m)) {
                sb.append(",").append(m);
            }
        }
        if (sb.length() > 0) {
            return sb.substring(1);
        }
        return "";
    }

}
