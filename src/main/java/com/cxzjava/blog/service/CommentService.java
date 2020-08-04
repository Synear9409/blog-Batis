package com.cxzjava.blog.service;

import com.cxzjava.blog.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);     ///获取每个博客的评论列表

    List<Comment> listReplyComment(Long blogId);

    int saveComment(Comment comment);    //保存一个评论
}
