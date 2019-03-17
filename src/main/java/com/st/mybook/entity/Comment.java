package com.st.mybook.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: luotao
 * @CreateTime: 2019-03-16 15:49
 * @Description: 评论表
 */
@Data
public class Comment {
    private Integer id;
    private Integer aId;
    private Timestamp createTime;
    private String content;
    private Integer star;
}
