package com.noscale.edelweiss.data.source.remote.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class BookingSubmissionRequest {

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    @SerializedName("eventDateTime")
    @Expose
    private String eventDateTime;

    @SerializedName("weddingCategory")
    @Expose
    private int weddingCategory;

    @SerializedName("weddingPackage")
    @Expose
    private int weddingPackage;

    @SerializedName("weddingBuilding")
    @Expose
    private int weddingBuilding;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public int getWeddingCategory() {
        return weddingCategory;
    }

    public void setWeddingCategory(int weddingCategory) {
        this.weddingCategory = weddingCategory;
    }

    public int getWeddingPackage() {
        return weddingPackage;
    }

    public void setWeddingPackage(int weddingPackage) {
        this.weddingPackage = weddingPackage;
    }

    public int getWeddingBuilding() {
        return weddingBuilding;
    }

    public void setWeddingBuilding(int weddingBuilding) {
        this.weddingBuilding = weddingBuilding;
    }
}
