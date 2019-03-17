package com.st.mybook.service.impl;

import com.github.pagehelper.Page;
import com.st.mybook.entity.Comment;
import com.st.mybook.mapper.CommentMapper;
import com.st.mybook.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: luotao
 * @CreateTime: 2019-03-16 16:10
 * @Description: 评论服务实现
 */
@Service
public class CommentSeviceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getComment(Integer id) {
        return commentMapper.getComment(id);
    }

    @Override
    public Comment getIdAndStar(Integer id) {
        return commentMapper.getIdAndStar(id);
    }

    @Override
    public boolean updateStar(Comment comment) {
        if(commentMapper.updateStar(comment).equals(0)) {
            return false;
        }
        return true;
    }

    @Override
    public Page<Comment> getNewComment(Integer id) {
        return commentMapper.getNewComment(id);
    }

    @Override
    public Boolean pushComment(Comment comment) {
        if (commentMapper.pushComment(comment).equals(0)){
            return false;
        }
        return true;
    }
}
