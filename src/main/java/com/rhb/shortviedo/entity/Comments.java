package com.rhb.shortviedo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 课程评论表(Comments)实体类
 *
 * @author makejava
 * @since 2020-04-06 12:55:56
 */
public class Comments implements Serializable {
    private static final long serialVersionUID = -25857742272293574L;
    
    private String id;
    
    private String fatherCommentId;
    
    private String toUserId;
    /**
    * 视频id
    */
    private String videoId;
    /**
    * 留言者，评论的用户id
    */
    private String fromUserId;
    /**
    * 评论内容
    */
    private String comment;
    
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}