package com.st.mybook.service.impl;

import com.github.pagehelper.Page;
import com.st.mybook.entity.Article;
import com.st.mybook.mapper.ArticleMapper;
import com.st.mybook.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: luotao
 * @CreateTime: 2019-03-14 23:13
 * @Description: 文章服务
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Override
    /*
     * @Author luotao
     * @Description 文章的list
     * @Date 11:15 2019/3/15
     * @Param []
     * @return java.util.List<com.st.mybook.entity.Article>
     **/
    public Page<Article> getAllArticle() {
        Page<Article> articles = articleMapper.getAllArticle();
        //System.out.println(articles);
        for (Article article:articles) {
            article.setContent(article.getContent().substring(0, 100)+"...");
        }
        return articles;
    }
    /*
     * @Author luotao
     * @Description 阅读数最多的三篇文章
     * @Date 11:26 2019/3/15
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    @Override
    public List<Article> getHotArticle() {
        return articleMapper.getHotArticle();
    }

    /*
     * @Author luotao
     * @Description 查询单个文章
     * @Date 19:48 2019/3/15
     * @Param []
     * @return com.st.mybook.entity.Article
     **/
    @Override
    public Article getArticle(Integer id) {
        return articleMapper.getArticle(id);
    }

    /*
     * @Author luotao
     * @Description 更新阅读数
     * @Date 20:22 2019/3/15
     * @Param [readNum]
     * @return void
     **/
    @Override
    public void updateReadNum(Article article) {
        articleMapper.updateReadNum(article);
    }

    /*
     * @Author luotao
     * @Description 保存文章和检查文章是否合乎规范
     * @Date 16:30 2019/3/16
     * @Param [request]
     * @return boolean
     **/
    @Override
    public boolean insertArticle(HttpServletRequest request) {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setCategory(request.getParameter("category"));
        article.setAuthor(request.getParameter("author"));
        article.setContent(request.getParameter("content"));
        if(StringUtils.isEmpty(article.getContent())){
            return false;
        }
        if (article.getContent().length()<200){
            return false;
        }
        if(articleMapper.InsertArticle(article).equals(0)){
            return false;
        }
        return true;
    }

}
