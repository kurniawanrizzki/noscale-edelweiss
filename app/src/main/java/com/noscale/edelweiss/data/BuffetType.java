package com.noscale.edelweiss.data;

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
    private List<DetailBuffet> detailBuffets;

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

    public List<DetailBuffet> getDetailBuffets() {
        return detailBuffets;
    }

    public void setDetailBuffets(List<DetailBuffet> detailBuffets) {
        this.detailBuffets = detailBuffets;
    }
}
