package com.noscale.edelweiss.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackage implements Parcelable {

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

    protected WeddingPackage(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readString();
        totalBuffet = in.readString();
        buffetId = in.readInt();
        buffetName = in.readString();
        detailBuffets = in.createTypedArrayList(WeddingBuffet.CREATOR);
        detailPackages = in.createTypedArrayList(WeddingPackageDetail.CREATOR);
        bonus = in.createStringArrayList();
    }

    public static final Creator<WeddingPackage> CREATOR = new Creator<WeddingPackage>() {
        @Override
        public WeddingPackage createFromParcel(Parcel in) {
            return new WeddingPackage(in);
        }

        @Override
        public WeddingPackage[] newArray(int size) {
            return new WeddingPackage[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(totalBuffet);
        parcel.writeInt(buffetId);
        parcel.writeString(buffetName);
        parcel.writeTypedList(detailBuffets);
        parcel.writeTypedList(detailPackages);
        parcel.writeStringList(bonus);
    }
}
