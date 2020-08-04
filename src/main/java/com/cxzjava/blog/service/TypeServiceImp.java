package com.cxzjava.blog.service;

import com.cxzjava.blog.dao.TypeDao;
import com.cxzjava.blog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "typeCache")
public class TypeServiceImp implements TypeService {

    @Autowired
    TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }


    /**
     * 获取全部分类
     * @return*/

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    @Cacheable(key = "methodName")
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }


    /**
     * 获取主页博客列表的所属分类
     * @return*/

    @Override
    @Cacheable(key = "methodName")
    public List<Type> listBlogType() {
        return typeDao.getBlogType();
    }


    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }
}
