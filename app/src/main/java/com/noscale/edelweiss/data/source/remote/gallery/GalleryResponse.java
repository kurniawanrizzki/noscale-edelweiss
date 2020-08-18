package com.noscale.edelweiss.data.source.remote.gallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.Gallery;
import com.noscale.edelweiss.data.source.remote.BaseResponse;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class GalleryResponse extends BaseResponse {

    @SerializedName("weddingGalleries")
    @Expose
    private List<Gallery> galleries;

    public List<Gallery> getGalleries () {
        return galleries;
    }

}
