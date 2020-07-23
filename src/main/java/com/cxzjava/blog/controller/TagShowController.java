package com.cxzjava.blog.controller;

import com.cxzjava.blog.pojo.Blog;
import com.cxzjava.blog.pojo.BlogAndTag;
import com.cxzjava.blog.pojo.Tag;
import com.cxzjava.blog.service.BlogService;
import com.cxzjava.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagsService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(required = false,defaultValue = "1",value = "pagenum") int pagenum,
                        @PathVariable Long id, Model model){
        ///相当于获取了全部标签，标签不可能多到10000条
        PageHelper.startPage(pagenum,100);
        List<Tag> tags = tagsService.getAllTag();
        if(id == -1){
            ///获取第一个标签的id
            id = tags.get(0).getId();
        }
        List<Blog> blogs = blogService.getBlogByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tag",tags);
        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTagId",id);
        return "tags";
    }

}
