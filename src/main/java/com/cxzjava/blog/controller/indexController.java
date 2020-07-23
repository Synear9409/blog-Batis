package com.cxzjava.blog.controller;

import com.cxzjava.blog.pojo.Blog;
import com.cxzjava.blog.service.BlogService;
import com.cxzjava.blog.service.TagService;
import com.cxzjava.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pagenum") int pagenum, Model model){
        PageHelper.startPage(pagenum,4);
        List<Blog> allBlog = blogService.getIndexBlog();
        ///得到分页结果
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("page",pageInfo);

        model.addAttribute("type",typeService.listBlogType());
        model.addAttribute("tag",tagService.listBlogTag());
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop());
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query,Model model){
        model.addAttribute("query",query);
        return  "forward:/search/"+query;
        /**
         * 点击搜索 请求"/search" ---->>>>
         *                  转发请求【将query参数传递】  ---->>>>
         *                                             【post请求"/search/{query}" 获取到分页数据】
         *
         * 在分页按钮上获取query参数，拼接地址，点击则是以get的方式请求"/search/{query}" 实现分页跳转
         */

    }

    @RequestMapping("/search/{query}")
    public String searchPage(@RequestParam(required = false,defaultValue = "1",value = "pagenum") int pagenum,
                             @PathVariable String query,Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> searchBlog = blogService.getSearchBlog("%"+query+"%");
        PageInfo<Blog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("page",pageInfo);
        return "search";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getDetailedBlog(id));
        return "blogs";
    }

    @GetMapping("/footer/newblog")
    public String newblog(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop());
        return "_fragments :: newblogList";
    }
}
