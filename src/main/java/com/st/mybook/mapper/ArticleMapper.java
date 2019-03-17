package com.st.mybook.mapper;

import com.github.pagehelper.Page;
import com.st.mybook.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @Author: luotao
 * @CreateTime: 2019-03-14 22:22
 * @Description:
 */
@Mapper
public interface ArticleMapper {
    @Select("select * from article order by id desc")
    Page<Article> getAllArticle();

    @Select("select id,title from article order by read_num desc limit 3")
    List<Article> getHotArticle();

    @Select("select * from article where id = #{id}")
    Article getArticle(Integer id);

    @Update("update article set read_num = #{readNum} where id = #{id}")
    void updateReadNum(Article article);

    @Insert("insert into article(title,category,author,content) values(#{title},#{category}" +
            ",#{author},#{content})")
    Integer InsertArticle(Article article);
}
