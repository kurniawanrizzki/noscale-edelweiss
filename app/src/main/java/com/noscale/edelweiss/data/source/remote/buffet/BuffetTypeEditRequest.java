package com.noscale.edelweiss.data.source.remote.buffet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class BuffetTypeEditRequest {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("buffetId")
    @Expose
    private int buffetId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuffetId() {
        return buffetId;
    }

    public void setBuffetId(int buffetId) {
        this.buffetId = buffetId;
    }
}
