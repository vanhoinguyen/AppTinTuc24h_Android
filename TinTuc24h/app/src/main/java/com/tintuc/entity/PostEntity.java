package com.tintuc.entity;

import org.json.JSONObject;

import java.io.Serializable;

public class PostEntity implements Serializable {

//    {
//        "post_id": "7",
//            "post_title": "Hello",
//            "post_desc": "Hello",
//            "post_thumb": "Hello",
//            "catelory_id": "2"
//    },

    private int id;
    private String title;
    private String desc;
    private String thumb;
    private String content;
    private int cateloryId;

    public PostEntity(){}

    public PostEntity(JSONObject jsonObject){
        id=jsonObject.optInt("post_id",0);
        title=jsonObject.optString("post_title","");
        desc=jsonObject.optString("post_desc","");
        thumb=jsonObject.optString("post_thumb","");
        cateloryId=jsonObject.optInt("catelory_id",0);
        content=jsonObject.optString("post_content","");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getThumb() {
        return thumb;
    }

    public int getCateloryId() {
        return cateloryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setCateloryId(int cateloryId) {
        this.cateloryId = cateloryId;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", thumb='" + thumb + '\'' +
                ", content='" + content + '\'' +
                ", cateloryId=" + cateloryId +
                '}';
    }
}
