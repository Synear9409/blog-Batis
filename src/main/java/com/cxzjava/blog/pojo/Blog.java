package com.cxzjava.blog.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Blog {

    private Long id;
    @NotBlank(message="博客标题不能为空")
    private String title;
    private String content;
    //首图
    private String firstPicture;
    private String flag;
    private Integer views;
    //是否赞赏
    private boolean appreciation;
    //是否转载
    private boolean shareStatement;
    //是否含有评论
    private boolean commendable;
    ////状态
    private boolean published;
    ///推荐
    private boolean recommend;
    private Date createTime;
    private Date updateTime;


    ///获取多个标签的id
    private String tagIds;
    private String description;

    private Type type;
    private User user;

    //用于mybatis中进行连接查询
    private Long typeId;
    private Long userId;

    private List<Comment> comments = new ArrayList<>();

    ///新增一个tags时，也会将其保存到数据库中  ---级联新增
    private List<Tag> tags = new ArrayList<>();

    public Blog(){

    }

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

/**
     * 将所选标签(tags)List集合转换成String类型
     * @param tags
     * @return
     *
     * 转换后：1,2,3,4*/


    public String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag : tags){
                if(flag){
                    ids.append(",");
                }else{
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else{
            return tagIds;
        }
    }
}
