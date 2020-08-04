package com.cxzjava.blog.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Tag implements Serializable {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Tag(){

    }
}
