package com.st.mybook.mapper;


import com.github.pagehelper.Page;
import com.st.mybook.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment where a_id=#{id} order by star desc limit 3")
    List<Comment> getComment(Integer id);

    @Update("update comment set star = #{star} where id = #{id}")
    Integer updateStar(Comment comment);

    @Select("select id,star from comment where id = #{id}")
    Comment getIdAndStar(Integer id);

    @Select("select * from comment where a_id =#{id} order by id desc ")
    Page<Comment> getNewComment(Integer id);

    @Insert("insert into comment(a_id,content) values(#{aId},#{content})")
    Integer pushComment(Comment comment);
}
