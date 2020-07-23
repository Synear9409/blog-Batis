package com.cxzjava.blog.service;

import com.cxzjava.blog.dao.BlogDao;
import com.cxzjava.blog.exception.NotFoundException;
import com.cxzjava.blog.pojo.Blog;
import com.cxzjava.blog.pojo.BlogAndTag;
import com.cxzjava.blog.pojo.Tag;
import com.cxzjava.blog.pojo.Type;
import com.cxzjava.blog.util.MarkdownUtils;
import com.cxzjava.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogDao.searchAllBlog(blog);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        blogDao.updateView(id);
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        List<Blog> allBlog = blogDao.getBlogs();
        return allBlog;
    }

    @Override
    public List<Blog> getBlogByTypeId(Long id) {
        List<Blog> blog = blogDao.getByTypeId(id);
        return blog;
    }

    @Override
    public List<Blog> getBlogByTagId(Long id) {
        List<Blog> blog = blogDao.getByTagId(id);
        return blog;
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Override
    public List<Blog> listRecommendBlogTop() {
        return blogDao.listRecommendBlogTop();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public int countBlog() {
        return blogDao.getBlogs().size();
    }

    ///新增
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        ///保存博客
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将数据保存到t_blog_tags表
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for(Tag tag : tags){
            ////新增的时候无法获取自增的id，需在mybatis中设置
                blogAndTag = new BlogAndTag(tag.getId(),id);
                blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    //编辑
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blog_tags表中
        List<Tag> tags = blog.getTags();
        blogDao.deleteBlogAndTag(blog.getId());
        BlogAndTag blogAndTag = null;
        for(Tag tag : tags){
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        blogDao.deleteBlogAndTag(id);
        return blogDao.deleteBlog(id);
    }

//    @Override
//    public int updateView() {
//        return blogDao.updateView();
//    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Map<String,List<Blog>> map = new LinkedHashMap<>();
        for(String year : years){
            map.put(year,blogDao.findByYear(year));
        }
        return map;
    }
}
