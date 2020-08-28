package com.noscale.edelweiss.payment;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.PaymentType;

import java.math.BigInteger;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface PaymentContract {
    interface View extends BaseView<Presenter> {
        void openGallery ();
        void appendData (List<PaymentType> types, List<Booking> bookings);
        void showErrorMessage (String message, Runnable r);
        void showSuccessMessage ();
    }

    interface Presenter extends BasePresenter {
        void setBooking (Booking booking);
        String getBooking ();
        void setPaymentType (PaymentType type);
        int getPaymentType ();
        void fetch ();
        void submit (String receipt, BigInteger amount);
    }
}
