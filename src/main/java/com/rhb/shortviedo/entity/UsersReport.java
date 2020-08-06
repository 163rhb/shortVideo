package com.rhb.shortviedo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 举报用户表(UsersReport)实体类
 *
 * @author makejava
 * @since 2020-04-06 12:57:24
 */
public class UsersReport implements Serializable {
    private static final long serialVersionUID = 715688404772686789L;
    
    private String id;
    /**
    * 被举报用户id
    */
    private String dealUserId;
    
    private String dealVideoId;
    /**
    * 类型标题，让用户选择，详情见 枚举
    */
    private String title;
    /**
    * 内容
    */
    private String content;
    /**
    * 举报人的id
    */
    private String userid;
    /**
    * 举报时间
    */
    private Date createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealVideoId() {
        return dealVideoId;
    }

    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}