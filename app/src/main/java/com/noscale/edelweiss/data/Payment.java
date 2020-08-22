package com.noscale.edelweiss.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class Payment implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("idBooking")
    @Expose
    private int bookingId;

    @SerializedName("idPaymentType")
    @Expose
    private int paymentTypeId;

    @SerializedName("paymentType")
    @Expose
    private String paymentTypeName;

    @SerializedName("paymentDate")
    @Expose
    private String paymentDate;

    @SerializedName("paymentTotal")
    @Expose
    private BigInteger paymentTotal;

    @SerializedName("paymentReceipt")
    @Expose
    private String paymentReceipt;

    protected Payment(Parcel in) {
        id = in.readInt();
        bookingId = in.readInt();
        paymentTypeId = in.readInt();
        paymentTypeName = in.readString();
        paymentDate = in.readString();
        paymentTotal = new BigInteger(in.readString());
        paymentReceipt = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

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

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public BigInteger getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(BigInteger paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(bookingId);
        parcel.writeInt(paymentTypeId);
        parcel.writeString(paymentTypeName);
        parcel.writeString(paymentDate);
        parcel.writeString(paymentTotal.toString());
        parcel.writeString(paymentReceipt);
    }
}
