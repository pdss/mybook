package com.st.mybook.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: luotao
 * @CreateTime: 2019-03-14 22:15
 * @Description: 文章表
 */
@Data
public class Article {
    private Integer id;
    private String title;
    private String category;
    private Timestamp createTime;
    private String author;
    private Integer readNum;
    private String content;
    private Integer commentNum;
}
