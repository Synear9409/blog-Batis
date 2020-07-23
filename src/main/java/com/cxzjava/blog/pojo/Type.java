package com.cxzjava.blog.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class Type {

    private Long id;
    @NotBlank(message="分类名称不能为空")
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Type(){

    }
}
