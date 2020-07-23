package com.cxzjava.blog.service;

import com.cxzjava.blog.pojo.Tag;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    int updateTag(Tag tag);

    List<Tag> getAllTag();

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listBlogTag();

    int deleteTag(Long id);

    Tag getTagByName(String name);
}
