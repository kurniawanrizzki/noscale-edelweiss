package com.noscale.edelweiss.data.source.remote.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.PaymentHistory;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PaymentResponse extends BaseResponse {

    @SerializedName("paymentHistories")
    @Expose
    private List<PaymentHistory> paymentHistories;

    public List<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }
}
