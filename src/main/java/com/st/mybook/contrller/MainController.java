package com.st.mybook.contrller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.st.mybook.entity.Article;
import com.st.mybook.entity.Comment;
import com.st.mybook.result.JsonResult;
import com.st.mybook.service.CommentService;
import com.st.mybook.service.impl.ArticleServiceImpl;
import com.st.mybook.service.impl.CommentSeviceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: luotao
 * @CreateTime: 2019-03-13 16:01
 * @Description:
 */
@Controller
@Slf4j
public class MainController {
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private CommentSeviceImpl commentSevice;

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/full-width")
    public String blog(){
        return "full-width";
    }

    //主页
    @GetMapping(value = {"/index","/","/1","2","3"})
    public String index(Model model,
                        @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize" ,defaultValue = "5")Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> articles = articleService.getAllArticle();
        System.out.println(articles);
        List<Article> hotArticles = articleService.getHotArticle();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("articles", articles);
        model.addAttribute("hotArticles", hotArticles);
        return "index";
    }

    //文章主体页面
    @GetMapping("/single")
    public String single(Model model,
                         @RequestParam("id") Integer id,
                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                         @RequestParam(value = "pageSize" ,defaultValue = "5")Integer pageSize){
        Article article = articleService.getArticle(id);
        article.setReadNum(article.getReadNum()+1);
        articleService.updateReadNum(article);
        List<Comment> hotComments = commentSevice.getComment(article.getId());
        PageHelper.startPage(pageNum, pageSize);
        Page<Comment> newComments = commentSevice.getNewComment(article.getId());
        System.out.println(newComments);
        model.addAttribute("article", article);
        model.addAttribute("hotComments", hotComments);
        model.addAttribute("newComments", newComments);
        return "single";
    }
    //上传文章页面
    @GetMapping("/ltup")
    public String ltup(){
        return "article-upload";
    }

    //上传文章
    @PostMapping("/upload")
    public String upload(HttpServletRequest request, Model model){
        if(articleService.insertArticle(request)){
            model.addAttribute("msg", "保存成功");
        }else {
            model.addAttribute("msg", "保存失败");
        }
        return "article-upload";
    }
    //点赞
    @PostMapping("/add")
    @ResponseBody
    public void addStar(@RequestParam("id") Integer id){
        Comment comment = commentSevice.getIdAndStar(id);
        comment.setStar(comment.getStar()+1);
        if(commentSevice.updateStar(comment)){
            System.out.println("ok");
        }

    }
    //取消点赞
    @PostMapping("/sub")
    @ResponseBody
    public void subStar(@RequestParam("id") Integer id){
        Comment comment = commentSevice.getIdAndStar(id);
        comment.setStar(comment.getStar()-1);
        if(commentSevice.updateStar(comment)){
            System.out.println("ok");
        }
    }
    //发表评论
    @PostMapping("/pushComment")
    @ResponseBody
    public Map<String,String> pushComment(Comment comment){
        //Comment comment = new Comment();
//        comment.setAId(id);
//        comment.setContent(content);
        System.out.println(comment);
        Map<String,String> map = new HashMap<>();
        JsonResult result = new JsonResult();
        if(commentSevice.pushComment(comment)){
            map.put("statu", "发表成功");
        }else {
            map.put("statu", "发表失败");
        }
        return map;
    }
}
