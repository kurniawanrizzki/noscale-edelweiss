package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.PaymentHistory;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.data.source.remote.payment.PaymentSubmissionRequest;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface PaymentDataSource {
    interface GetLoadCallback {
        void onLoadPaymentType (List<PaymentType> types, List<Booking> bookings);
        void onFailurePaymentType (String message);
    }

    interface PostLoadCallback {
        void onSuccess ();
        void onError (String message);
    }

    interface GetCallback {
        void onSuccess (List<PaymentHistory> data);
        void onError (String message);
    }

    void getPaymentList (GetCallback callback);

    void getPaymentTypes (int userId, GetLoadCallback callback);

    void submit (PaymentSubmissionRequest request, PostLoadCallback callback);
}
