package com.cxzjava.blog.pojo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Comment implements Serializable {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;

    private Blog blog;

    ////回复的评论
    private List<Comment> ReplyComments = new ArrayList<>();

    private boolean adminComment;  ///是否为管理员评论信息
    private Comment parentComment;///父评论

    public Comment(){

    }
}
