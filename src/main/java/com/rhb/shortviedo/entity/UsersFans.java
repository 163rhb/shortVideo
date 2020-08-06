package com.rhb.shortviedo.entity;

import java.io.Serializable;

/**
 * 用户粉丝关联关系表(UsersFans)实体类
 *
 * @author makejava
 * @since 2020-04-06 12:56:49
 */
public class UsersFans implements Serializable {
    private static final long serialVersionUID = 186274783324755141L;
    
    private String id;
    /**
    * 用户
    */
    private String userId;
    /**
    * 粉丝
    */
    private String fanId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFanId() {
        return fanId;
    }

    public void setFanId(String fanId) {
        this.fanId = fanId;
    }

}