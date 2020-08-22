package com.noscale.edelweiss.data.source.remote.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class PaymentSubmissionRequest {

    @SerializedName("paymentTypeId")
    @Expose
    private int paymentTypeId;

    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;

    @SerializedName("receipt")
    @Expose
    private String receipt;

    @SerializedName("amount")
    @Expose
    private String amount;

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
