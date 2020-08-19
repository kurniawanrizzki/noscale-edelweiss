package com.noscale.edelweiss.data.source.remote.gallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class GallerySubmissionRequest {

    @SerializedName("weddingCategoryId")
    @Expose
    private int categoryId;

    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
