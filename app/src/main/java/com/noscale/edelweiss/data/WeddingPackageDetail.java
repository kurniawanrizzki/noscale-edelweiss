package com.noscale.edelweiss.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class WeddingPackageDetail implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("details")
    @Expose
    private List<String> details;

    protected WeddingPackageDetail(Parcel in) {
        id = in.readInt();
        name = in.readString();
        details = in.createStringArrayList();
    }

    public static final Creator<WeddingPackageDetail> CREATOR = new Creator<WeddingPackageDetail>() {
        @Override
        public WeddingPackageDetail createFromParcel(Parcel in) {
            return new WeddingPackageDetail(in);
        }

        @Override
        public WeddingPackageDetail[] newArray(int size) {
            return new WeddingPackageDetail[size];
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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeStringList(details);
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj instanceof WeddingPackageDetail) {
            WeddingPackageDetail tmp = (WeddingPackageDetail) obj;
            return tmp.getId() == id;
        }

        return super.equals(obj);
    }
}
