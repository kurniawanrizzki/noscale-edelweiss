package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.PaymentType;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface PaymentDataSource {
    interface GetLoadCallback {
        void onLoadPaymentType (List<PaymentType> types);
        void onFailurePaymentType (String message);
    }

    void getPaymentTypes (GetLoadCallback callback);
}
