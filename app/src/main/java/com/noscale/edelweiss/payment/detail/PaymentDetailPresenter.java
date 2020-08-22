package com.noscale.edelweiss.payment.detail;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PaymentDetailPresenter implements PaymentDetailContract.Presenter {

    private PaymentDetailContract.View mView;

    public PaymentDetailPresenter (PaymentDetailContract.View view) {
        view.setPresenter(this);

        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
