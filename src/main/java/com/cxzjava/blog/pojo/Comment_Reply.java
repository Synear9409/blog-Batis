package com.cxzjava.blog.pojo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment_Reply {
    private Long parentId;
    private Long replyId;

    public Comment_Reply(){

    }
    public Comment_Reply(Long parentId,Long replyId){
        this.parentId = parentId;
        this.replyId = replyId;
    }
}
