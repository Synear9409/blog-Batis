package com.cxzjava.blog.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Tag{
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Tag(){

    }
}
