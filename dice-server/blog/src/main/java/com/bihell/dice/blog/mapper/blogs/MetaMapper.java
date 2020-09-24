package com.bihell.dice.blog.mapper.blogs;

import com.bihell.dice.blog.model.dto.MetaDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.blog.model.blog.Meta;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Meta Mapper
 *
 * @author bihell
 * @since 2017/8/28 23:40
 */
public interface MetaMapper extends BaseMapper<Meta> {

    /**
     * 根据文章id获取该文章下的属性
     *
     * @param articleId 文章id
     * @param type      属性类型
     * @return List<Meta>
     */
    @Select("SELECT * FROM dice.meta WHERE type = #{type} AND id IN (SELECT m_id FROM dice.middle WHERE a_id = #{articleId})")
    List<Meta> selectByArticle(@Param("articleId") Integer articleId, @Param("type") String type);


    /**
     * 获取属性以及属性下的文章
     *
     * @param type 标签类型
     * @param title 文章标题（目前只有代码段功能使用）
     * @param snippetFileContent 代码段文件内容
     * @return  List<MetaDto>
     */
    @SelectProvider(type = MetaSql.class, method = "selectMetaDtos")
    List<MetaDto> selectMetaDtos(@Param("type") String type,
                                 @Param("title") String title,
                                 @Param("snippetFileContent") String snippetFileContent);

    /**
     * 获取属性以及属性下的已发布文章 todo 修改 @One xxx这种方式
     *
     * @param type 属性类型
     * @return List<MetaDto>
     */
    @Select("select * from dice.meta meta where meta.type = #{type}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "id", property = "count",
                    one = @One(select = "com.bihell.dice.blog.mapper.blogs.ArticleMapper.selectPublishCountByMeta")),
            @Result(column = "id", property = "articles",
                    many = @Many(select = "com.bihell.dice.blog.mapper.blogs.ArticleMapper.selectPublishByMeta"))
    })
    List<MetaDto> selectPublishMetaDtos(@Param("type") String type);
}
