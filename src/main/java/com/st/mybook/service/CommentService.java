package com.st.mybook.service;

import com.github.pagehelper.Page;
import com.st.mybook.entity.Comment;

import java.util.List;

public interface CommentService {
    //通过文章id查询最火的三条评论
    List<Comment> getComment(Integer id);
    //通过评论id查询评论
    Comment getIdAndStar(Integer id);
    //更新点赞数
    boolean updateStar(Comment comment);
    //全部评论 从最新开始
    Page<Comment> getNewComment(Integer id);
    //发表评论
    Boolean pushComment(Comment comment);
}
