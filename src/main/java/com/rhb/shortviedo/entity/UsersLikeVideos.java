package com.rhb.shortviedo.entity;

import java.io.Serializable;

/**
 * 用户喜欢的/赞过的视频(UsersLikeVideos)实体类
 *
 * @author makejava
 * @since 2020-04-06 12:57:11
 */
public class UsersLikeVideos implements Serializable {
    private static final long serialVersionUID = 344163761921062996L;
    
    private String id;
    /**
    * 用户
    */
    private String userId;
    /**
    * 视频
    */
    private String videoId;


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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}