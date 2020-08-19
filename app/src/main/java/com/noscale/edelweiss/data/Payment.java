package com.noscale.edelweiss.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class Payment {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("idBooking")
    @Expose
    private int bookingId;

    @SerializedName("idPaymentType")
    @Expose
    private int paymentTypeId;

    @SerializedName("paymentDate")
    @Expose
    private String paymentDate;

    @SerializedName("paymentReceipt")
    @Expose
    private String paymentReceipt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(String paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }
}
