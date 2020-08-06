package com.rhb.shortviedo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 视频信息表(Videos)实体类
 *
 * @author makejava
 * @since 2020-04-06 12:58:50
 */
public class Videos implements Serializable {
    private static final long serialVersionUID = 588606532108034078L;
    
    private String id;
    /**
    * 发布者id
    */
    private String userId;
    /**
    * 用户使用音频的信息
    */
    private String audioId;
    /**
    * 视频描述
    */
    private String videoDesc;
    /**
    * 视频存放的路径
    */
    private String videoPath;
    /**
    * 视频秒数
    */
    private Object videoSeconds;
    /**
    * 视频宽度
    */
    private Integer videoWidth;
    /**
    * 视频高度
    */
    private Integer videoHeight;
    /**
    * 视频封面图
    */
    private String coverPath;
    /**
    * 喜欢/赞美的数量
    */
    private Long likeCounts;
    /**
    * 视频状态：
1、发布成功
2、禁止播放，管理员操作
    */
    private Integer status;
    /**
    * 创建时间
    */
    private Date createTime;


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

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Object getVideoSeconds() {
        return videoSeconds;
    }

    public void setVideoSeconds(Object videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public Long getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}