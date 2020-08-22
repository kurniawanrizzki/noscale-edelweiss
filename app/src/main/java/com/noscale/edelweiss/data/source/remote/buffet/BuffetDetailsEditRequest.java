package com.noscale.edelweiss.data.source.remote.buffet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class BuffetDetailsEditRequest {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("detailBuffetIds")
    @Expose
    private Integer[] detailBuffetIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer[] getDetailBuffetIds() {
        return detailBuffetIds;
    }

    public void setDetailBuffetIds(Integer[] detailBuffetIds) {
        this.detailBuffetIds = detailBuffetIds;
    }
}
