package com.cxzjava.blog.service;

import com.cxzjava.blog.pojo.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> listType();          //后台下拉框分类组

    List<Type> getAllType();        //前台分类页头部分类组的展示

    List<Type> listBlogType();      ///前台右侧分类的显示

    int updateType(Type type);

    int deleteType(Long id);

    Type getTypeByName(String name);      ///后台新增修改操作判断是否重复加入已存在的分类

}
