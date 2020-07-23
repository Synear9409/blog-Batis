package com.cxzjava.blog.dao;

import com.cxzjava.blog.pojo.Blog;
import com.cxzjava.blog.pojo.BlogAndTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogDao {

    Blog getBlog(Long id);  ///后台展示博客

    Blog getDetailedBlog(@Param("id") Long id);  //前端博客详情

    List<Blog> getBlogs();

    List<Blog> getByTypeId(Long typeId);

    List<Blog> getByTagId(Long tagId);

    List<Blog> getIndexBlog();  //主页博客展示

    List<Blog> listRecommendBlogTop();  ///最新推荐博客

    List<Blog> getSearchBlog(String query);  //全局搜索博客

    List<Blog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客

    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<Blog> findByYear(@Param("year") String year);  //按年份查询博客

    int saveBlog(Blog blog);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    void deleteBlogAndTag(Long blogsId);

    int updateView(Long id);   ////更新浏览次数
}
