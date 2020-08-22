package com.noscale.edelweiss.payment.list;

import com.noscale.edelweiss.data.PaymentHistory;
import com.noscale.edelweiss.data.source.PaymentDataSource;
import com.noscale.edelweiss.data.source.remote.payment.PaymentRemoteDataSource;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PaymentListPresenter implements PaymentListContract.Presenter {

    private PaymentListContract.View mView;

    private boolean mIsDataMissing;

    public PaymentListPresenter (PaymentListContract.View view, boolean isDataMissing) {
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
        PaymentRemoteDataSource.getInstance().getPaymentList(new PaymentDataSource.GetCallback() {
            @Override
            public void onSuccess(List<PaymentHistory> data) {
                mView.append(data);
            }

            @Override
            public void onError(String message) {
                mView.showErrorMessage(message, () -> fetch());
            }
        });
    }
}
