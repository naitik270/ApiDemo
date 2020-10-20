package com.jakb.apidemo.Classes;

import android.nfc.Tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsList {
    
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("categories")
    @Expose
    private List<ClsCategory> categories = null;
    @SerializedName("tags")
    @Expose
    private List<ClsTag> tags = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    private boolean isDownloaded = false;

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ClsCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ClsCategory> categories) {
        this.categories = categories;
    }

    public List<ClsTag> getTags() {
        return tags;
    }

    public void setTags(List<ClsTag> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
