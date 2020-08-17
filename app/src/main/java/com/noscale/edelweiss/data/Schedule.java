package com.noscale.edelweiss.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.schedule.Status;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class Schedule {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;

    @SerializedName("firstname")
    @Expose
    private String firstName;

    @SerializedName("lastname")
    @Expose
    private String lastName;

    @SerializedName("dateTime")
    @Expose
    private String dateTime;

    @SerializedName("status")
    @Expose
    private Status status;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName () {
        return firstName+" "+lastName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
