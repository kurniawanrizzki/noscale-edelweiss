package com.noscale.edelweiss.data.source.remote.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class PaymentTypeResponse extends BaseResponse {

    @SerializedName("paymentTypes")
    @Expose
    private List<PaymentType> types;

    @SerializedName("bookingList")
    @Expose
    private List<Booking> bookings;

    public List<PaymentType> getTypes() {
        return types;
    }

    public List<Booking> getBookings () {
        return bookings;
    }
}
