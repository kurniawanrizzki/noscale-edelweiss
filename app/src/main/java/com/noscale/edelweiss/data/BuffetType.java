package com.noscale.edelweiss.data;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class BuffetType {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("detailBuffets")
    @Expose
    private List<WeddingBuffet> detailBuffets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeddingBuffet> getDetailBuffets() {
        return detailBuffets;
    }

    public void setDetailBuffets(List<WeddingBuffet> detailBuffets) {
        this.detailBuffets = detailBuffets;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj instanceof BuffetType) {
            BuffetType type = (BuffetType) obj;
            return type.getId() == id;
        }

        return super.equals(obj);
    }
}
