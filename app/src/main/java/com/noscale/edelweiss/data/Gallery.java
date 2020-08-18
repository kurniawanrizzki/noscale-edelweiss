package com.noscale.edelweiss.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class Gallery {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;


}
