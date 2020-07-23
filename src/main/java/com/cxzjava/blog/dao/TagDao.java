package com.cxzjava.blog.dao;

import com.cxzjava.blog.pojo.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {

    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();   ///后台标签页面

    List<Tag> getAllTag();   ///前台标签展示页面

    List<Tag> getBlogTag();  //首页展示博客所属标签列表

    int updateTag(Tag tag);

    int deleteTag(Long id);

}
