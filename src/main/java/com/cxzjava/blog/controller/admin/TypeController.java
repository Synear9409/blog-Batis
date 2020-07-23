package com.cxzjava.blog.controller.admin;

import com.cxzjava.blog.pojo.Tag;
import com.cxzjava.blog.pojo.Type;
import com.cxzjava.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

/**
     * 根据Type的分页
     * @param pagenum
     * @return*/


    @GetMapping("/type")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum") int pagenum, Model model){
        PageHelper.startPage(pagenum,5);
        List<Type> allType = typeService.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("page",pageInfo);
        return "admin/types";
    }

/**
     * 载入新增页面
     * @param model
     * @return*/


    @GetMapping("/type/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

/**
     * 载入更新操作页面
     * @param id
     * @param model
     * @return*/


    @GetMapping("/type/{id}/input")
    public String editInput(@PathVariable Long id , Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

/**
     * 分类的新增、修改操作
     * @param type
     * @param result
     * @param attributes
     * @return*/


    @PostMapping("/type/save")
    public String posType(@Valid Type type,
                          BindingResult result,
                          RedirectAttributes attributes){
        Type t1 = typeService.getTypeByName(type.getName());
        if(t1 != null){
            result.rejectValue("name","nameError","该分类已存在");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        if(type.getId() == null)
            //可插入数据也可以修改保存数据
            typeService.saveType(type);
        else
            typeService.updateType(type);

        attributes.addFlashAttribute("message","操作成功!");
        return "redirect:/admin/type";
    }

    @GetMapping("/type/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功!");
        return "redirect:/admin/type";
    }
}
