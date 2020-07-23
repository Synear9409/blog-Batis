package com.cxzjava.blog.dao;

import com.cxzjava.blog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

    User findByUsernameAndPassword(@Param("username") String name , @Param("password") String password);

}
