package com.bihell.dice.blog.mapper.blogs;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.blog.enums.PostStatusEnum;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.model.dto.ArticleInfoDto;
import com.bihell.dice.blog.utils.Types;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Article Mapper
 *
 * @author bihell
 * @since 2017/7/8 10:39
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据metaId获取关联的文章
     *
     * @param metaId 属性id
     * @return List<Article>
     */
    @Select("SELECT * FROM dice.article WHERE id IN (SELECT a_id FROM dice.middle WHERE m_id = #{metaId}) " +
            "AND dice.article.type = '" + Types.POST + "' order by create_time desc")
    List<Article> selectByMeta(@Param("metaId") Integer metaId);

    /**
     * 根据metaId获取关联文章的数量
     *
     * @param metaId 属性id
     * @return Integer
     */
    @Select("SELECT count(*) FROM dice.article WHERE id " +
            "IN (SELECT a_id FROM dice.middle WHERE m_id = #{metaId}) " +
            "AND dice.article.type = '" + Types.POST + " AND dice.article.deleted=0")
    Integer selectCountByMeta(@Param("metaId") Integer metaId);

    /**
     * 根据metaId获取关联的已发布文章
     *
     * @param metaId 属性id
     * @return List<Article>
     */
    @Select("SELECT * FROM dice.article WHERE id " +
            "IN (SELECT a_id FROM dice.middle WHERE m_id = #{metaId}) " +
            "AND dice.article.status = '" + PostStatusEnum.Constants.PUBLISHED_VALUE + "' AND dice.article.type = '" + Types.POST + "' order by create_time desc")
    List<ArticleInfoDto> selectPublishByMeta(@Param("metaId") Integer metaId);

    /**
     * 根据metaId获取关联的已发布代码段
     *
     * @param metaId 属性id
     * @return List<Article>
     */
    @Select("SELECT * FROM dice.article WHERE id " +
            "IN (SELECT a_id FROM dice.middle WHERE m_id = #{metaId}) order by create_time desc")
    List<ArticleInfoDto> selectSnippetByMeta(@Param("metaId") Integer metaId);

    /**
     * 根据metaId获取关联已发布文章的数量
     *
     * @param metaId 属性id
     * @return Integer
     */
    @Select("SELECT count(*) FROM dice.article WHERE id " +
            "IN (SELECT a_id FROM dice.middle WHERE m_id = #{metaId}) " +
            "AND dice.article.status = '" + PostStatusEnum.Constants.PUBLISHED_VALUE + "' AND dice.article.type = '" + Types.POST + "'")
    Integer selectPublishCountByMeta(@Param("metaId") Integer metaId);
}
