package com.noscale.edelweiss.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackage {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("totalBuffet")
    @Expose
    private String totalBuffet;

    @SerializedName("buffetId")
    @Expose
    private int buffetId;

    @SerializedName("buffetName")
    @Expose
    private String buffetName;

    @SerializedName("detailBuffets")
    @Expose
    private List<WeddingBuffet> detailBuffets;

    @SerializedName("detailPackages")
    @Expose
    private List<WeddingPackageDetail> detailPackages;

    @SerializedName("bonus")
    @Expose
    private List<String> bonus;

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

    public String getBuffetName() {
        return buffetName;
    }

    public void setBuffetName(String buffetName) {
        this.buffetName = buffetName;
    }

    public List<WeddingBuffet> getDetailBuffets() {
        return detailBuffets;
    }

    public void setDetailBuffets(List<WeddingBuffet> detailBuffets) {
        this.detailBuffets = detailBuffets;
    }

    public List<WeddingPackageDetail> getDetailPackages() {
        return detailPackages;
    }

    public void setDetailPackages(List<WeddingPackageDetail> detailPackages) {
        this.detailPackages = detailPackages;
    }

    public List<String> getBonus() {
        return bonus;
    }

    public void setBonus(List<String> bonus) {
        this.bonus = bonus;
    }
}
