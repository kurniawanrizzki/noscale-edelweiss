package com.noscale.edelweiss.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class WeddingBuffet implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    protected WeddingBuffet(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<WeddingBuffet> CREATOR = new Creator<WeddingBuffet>() {
        @Override
        public WeddingBuffet createFromParcel(Parcel in) {
            return new WeddingBuffet(in);
        }

        @Override
        public WeddingBuffet[] newArray(int size) {
            return new WeddingBuffet[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj instanceof WeddingBuffet) {
            WeddingBuffet tmp = (WeddingBuffet) obj;
            return tmp.getId() == id;
        }

        return super.equals(obj);
    }
}
