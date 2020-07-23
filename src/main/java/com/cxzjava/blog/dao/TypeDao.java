package com.cxzjava.blog.dao;

import com.cxzjava.blog.pojo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao {

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> listType();

    List<Type> getAllType();     //分类展示页面

    List<Type> getBlogType();  ///首页右侧博客列表的数值

    int updateType(Type type);

    int deleteType(Long id);

    int saveType(Type type);
}
