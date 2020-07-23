package com.cxzjava.blog.service;

import com.cxzjava.blog.pojo.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> listType();

    List<Type> getAllType();

    List<Type> listBlogType();

    int updateType(Type type);

    int deleteType(Long id);

    Type getTypeByName(String name);

}
