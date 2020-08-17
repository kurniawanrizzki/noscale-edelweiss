package com.noscale.edelweiss.data.source.remote.payment;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class PaymentSubmissionRequest {

    private int paymentTypeId;

    private String bookingNumber;

    private String receipt;

    public PaymentSubmissionRequest (int paymentTypeId, String bookingNumber, String receipt) {
        this.paymentTypeId = paymentTypeId;
        this.bookingNumber = bookingNumber;
        this.receipt = receipt;
    }

}
