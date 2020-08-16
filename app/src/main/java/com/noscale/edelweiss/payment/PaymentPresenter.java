package com.noscale.edelweiss.payment;

import com.noscale.edelweiss.data.source.PaymentDataSource;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.data.source.remote.payment.PaymentRemoteDataSource;
import java.util.List;

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

        fetch();
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    @Override
    public void fetch() {
        PaymentRemoteDataSource.getInstance().getPaymentTypes(new PaymentDataSource.GetLoadCallback() {
            @Override
            public void onLoadPaymentType(List<PaymentType> types) {
                mView.appendData(types);
            }

            @Override
            public void onFailurePaymentType(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
