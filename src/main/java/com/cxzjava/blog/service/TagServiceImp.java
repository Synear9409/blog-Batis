package com.cxzjava.blog.service;

import com.cxzjava.blog.dao.TagDao;
import com.cxzjava.blog.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImp implements TagService {

    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }


    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    //从tagIds字符串中获取id，根据id获取tag集合
    @Override
    public List<Tag> listTag(String ids) {
        List<Tag> tagList = new ArrayList<>();
        List<Long> tags = convertToList(ids);
        for(Long t : tags){
            tagList.add(tagDao.getTag(t));
        }
        return tagList;
    }

/**
     * 获取标签的前几条数据
     * @return*/

    @Override
    public List<Tag> listBlogTag() {
        return tagDao.getBlogTag();
    }

/**
     * 将用户所选的标签String字符串转换为list并存入数据库
     * @param ids
     * @return
     */

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idsArray = ids.split(",");
            for (int i = 0 ;i<idsArray.length;i++) {
                list.add(new Long(idsArray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }
}
