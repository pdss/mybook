package com.st.mybook.mapper;

import com.st.mybook.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleMapperTest {
    @Resource
    private ArticleMapper mapper;
    @Test
    public void test(){
        System.out.println(mapper.getAllArticle());
    }

}