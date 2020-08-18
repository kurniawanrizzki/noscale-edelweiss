package com.noscale.edelweiss.data.source.remote.wp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class PackageSubmissionRequest {

    @SerializedName("packageName")
    @Expose
    private String name;

    @SerializedName("packagePrice")
    @Expose
    private String price;

    @SerializedName("totalBuffet")
    @Expose
    private String totalBuffet;

    @SerializedName("buffetId")
    @Expose
    private int buffetId;

    @SerializedName("detailPackageIds")
    @Expose
    private Integer[] packageIds;

    @SerializedName("bonus")
    @Expose
    private String[] bonus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalBuffet() {
        return totalBuffet;
    }

    public void setTotalBuffet(String totalBuffet) {
        this.totalBuffet = totalBuffet;
    }

    public int getBuffetId() {
        return buffetId;
    }

    public void setBuffetId(int buffetId) {
        this.buffetId = buffetId;
    }

    public Integer[] getPackageIds() {
        return packageIds;
    }

    public void setPackageIds(Integer[] packageIds) {
        this.packageIds = packageIds;
    }

    public String[] getBonus() {
        return bonus;
    }

    public void setBonus(String[] bonus) {
        this.bonus = bonus;
    }
}
