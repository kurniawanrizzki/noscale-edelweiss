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
}
