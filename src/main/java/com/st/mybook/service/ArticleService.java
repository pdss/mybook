package com.st.mybook.service;

import com.st.mybook.entity.Article;
import com.st.mybook.entity.Comment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: luotao
 * @CreateTime: 2019-03-14 23:12
 * @Description:
 */
public interface ArticleService {
    //所有文章
    List<Article> getAllArticle();
    //前三阅读文章
    List<Article> getHotArticle();
    //查询单个文章
    Article getArticle(Integer id);
    //更新阅读数
    void updateReadNum(Article article);
    //插入文章
    boolean insertArticle(HttpServletRequest request);
}
