package com.cxzjava.blog.service;

import com.cxzjava.blog.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {

    ///获取每个博客的评论列表
    List<Comment> listCommentByBlogId(Long blogId);

    List<Comment> listReplyComment(Long blogId);

    //保存一个评论
    int saveComment(Comment comment);
}
