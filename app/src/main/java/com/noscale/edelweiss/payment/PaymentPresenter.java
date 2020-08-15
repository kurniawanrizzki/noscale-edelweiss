package com.noscale.edelweiss.payment;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class PaymentPresenter implements PaymentContract.Presenter {

    private PaymentContract.View mView;

    private boolean mIsDataMissing;

    public PaymentPresenter (PaymentContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!mIsDataMissing) return;
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
