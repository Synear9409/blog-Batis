package com.cxzjava.blog.controller;

import com.cxzjava.blog.pojo.Blog;
import com.cxzjava.blog.pojo.BlogAndTag;
import com.cxzjava.blog.pojo.Type;
import com.cxzjava.blog.service.BlogService;
import com.cxzjava.blog.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum") int pagenum,
                        @PathVariable Long id, Model model){
        PageHelper.startPage(pagenum,100);
        List<Type> types = typeService.getAllType();

        if(id == -1){
            id = types.get(0).getId();
        }
        List<Blog> blogs = blogService.getBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("type",types);
        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }

}
