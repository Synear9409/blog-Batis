package com.cxzjava.blog.pojo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogAndTag {

    private Long tagsId;
    private Long blogsId;

    public BlogAndTag(Long tagsId, Long blogsId) {
        this.tagsId = tagsId;
        this.blogsId = blogsId;
    }
    public BlogAndTag(){

    }
}
