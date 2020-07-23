package com.cxzjava.blog.service;
import com.cxzjava.blog.pojo.Blog;


import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    List<Blog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客

    Blog getDetailedBlog(Long id);  //前端展示博客详情

    List<Blog> getAllBlog();   ///后端博客列表

    List<Blog> getBlogByTypeId(Long typeId);

    List<Blog>  getBlogByTagId(Long tagId);

    List<Blog> getIndexBlog();  //主页博客展示

    List<Blog> listRecommendBlogTop(); ///最新推荐博客

    List<Blog> getSearchBlog(String query);  //全局搜索博客

    int countBlog();    ///博客条数

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    Map<String,List<Blog>> archiveBlog();  ///归档博客
}
