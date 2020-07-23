package com.cxzjava.blog.dao;

import com.cxzjava.blog.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    //根据创建时间倒序来排 获取父级评论
    List<Comment> findByBlogIdAndParentCommentsNull(@Param("blogId") Long blogId);

    ////获取回复的评论
    List<Comment> findByBlogIdAndParentCommentsNotNull(@Param("blogId") Long blogId);

    //查询父级对象
    Comment findParentComment(Long parentCommentId);

    //保存一个评论
    int saveComment(Comment comment);
}
