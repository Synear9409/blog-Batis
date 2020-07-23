package com.cxzjava.blog.controller.admin;


import com.cxzjava.blog.pojo.*;
import com.cxzjava.blog.service.BlogService;
import com.cxzjava.blog.service.TagService;
import com.cxzjava.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blog";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    public void setTypeAndTag(Model model){
        model.addAttribute("type",typeService.listType());
        model.addAttribute("tag",tagService.listTag());
    }

/**
     * 获取分页数据并返回到前端
     * @param pagenum
     * @param model
     * @return*/
    @GetMapping("/blog")
    public String blog(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> allBlog = blogService.getAllBlog();
        ////得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("page",pageInfo);
        setTypeAndTag(model);   ///获取所有标签以及分类
        return LIST;
    }

/**
     * 模糊查询
     * @param pagenum
     * @param blog
     * @param model
     * @return*/

    @PostMapping("/blog/search")
    public String search(Blog blog,@RequestParam(required = false,defaultValue = "1",value = "pagenum") int pagenum, Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> blogs = blogService.searchAllBlog(blog);
        ////得到分页结果对象
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("page",pageInfo);
        setTypeAndTag(model);
        return "admin/blogs :: blogList";
    }

/**
     * 载入博客新增页面
     * @param model
     * @return*/

    @GetMapping("/blog/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }


/**
     * 载入博客修改页面
     * @param id
     * @param model
     * @return
*/

    @GetMapping("/blog/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();  //将tags集合转换为tagIds字符串
        model.addAttribute("blog",blog);
        return INPUT;
    }

/**
     * 修改及新增
     * @param blog
     * @param attributes
     * @param session
     * @return*/

    @PostMapping("/blog/save")
    public String post(Blog blog, BindingResult result, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUserId());
        blog.setType(typeService.getType(blog.getTypeId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));


         /* 不建议再在这加这个标题验证了，会给修改博客时造成小bug
        Blog b1;
        b1 = blogService.getBlogByTitle(blog.getTitle());
        if(b1 != null){
            result.rejectValue("title","titleError","该博客标题已存在");
        }
        if(result.hasErrors())
            return INPUT;*/

        if (blog.getId() == null) {
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }

        attributes.addFlashAttribute("message", "操作成功");
        return REDIRECT_LIST;
    }

    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功!");
        return REDIRECT_LIST;
    }
}
