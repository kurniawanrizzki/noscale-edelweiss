package com.noscale.edelweiss.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class Booking {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    @SerializedName("category")
    @Expose
    private String categoryName;

    @SerializedName("weddingPackage")
    @Expose
    private String weddingPackage;

    @SerializedName("dateTime")
    @Expose
    private String dateTime;

    @SerializedName("paymentList")
    @Expose
    private List<Payment> paymentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getWeddingPackage() {
        return weddingPackage;
    }

    public void setWeddingPackage(String weddingPackage) {
        this.weddingPackage = weddingPackage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
