package com.cxzjava.blog.service;

import com.cxzjava.blog.dao.UserDao;
import com.cxzjava.blog.pojo.User;
import com.cxzjava.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "userCache")
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(key = "methodName")
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
