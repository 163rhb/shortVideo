package com.rhb.shortviedo.entity;

import java.io.Serializable;

/**
 * 视频搜索的记录表(SearchRecords)实体类
 *
 * @author makejava
 * @since 2020-04-06 12:56:15
 */
public class SearchRecords implements Serializable {
    private static final long serialVersionUID = -43879977570016957L;
    
    private String id;
    /**
    * 搜索的内容
    */
    private String content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}