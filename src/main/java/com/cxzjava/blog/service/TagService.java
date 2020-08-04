package com.cxzjava.blog.service;

import com.cxzjava.blog.pojo.Tag;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    int updateTag(Tag tag);

    List<Tag> getAllTag();         //前台标签页头部标签组的展示

    List<Tag> listTag();           ///后台下拉框标签组

    List<Tag> listTag(String ids);   ///后台获取用户选择的多个标签

    List<Tag> listBlogTag();     ///前台右侧标签的显示

    int deleteTag(Long id);

    Tag getTagByName(String name);      ///后台新增修改操作判断是否重复加入已存在的标签
}
