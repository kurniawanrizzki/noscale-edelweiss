package com.noscale.edelweiss.payment;

import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.source.PaymentDataSource;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.data.source.remote.payment.PaymentRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.payment.PaymentSubmissionRequest;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class PaymentPresenter implements PaymentContract.Presenter {

    private PaymentContract.View mView;

    private boolean mIsDataMissing;

    private AppConfiguration mConfiguration;

    private final PaymentSubmissionRequest mRequest = new PaymentSubmissionRequest();

    public PaymentPresenter (PaymentContract.View view, AppConfiguration configuration, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.mConfiguration = configuration;
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
    public void setBooking(Booking booking) {
        mRequest.setBookingNumber(booking.getBookingNumber());
    }

    @Override
    public void setPaymentType(PaymentType type) {
        mRequest.setPaymentTypeId(type.getId());
    }

    @Override
    public void fetch() {
        PaymentRemoteDataSource.getInstance().getPaymentTypes(mConfiguration.getAuthenticatedId(), new PaymentDataSource.GetLoadCallback() {
            @Override
            public void onLoadPaymentType(List<PaymentType> types, List<Booking> bookings) {
                mView.appendData(types, bookings);
            }

            @Override
            public void onFailurePaymentType(String message) {
                mView.showErrorMessage(message, () -> fetch());
            }
        });
    }

    @Override
    public void submit(String receipt, String amount) {

        mRequest.setAmount(amount);
        mRequest.setReceipt(receipt);

        PaymentRemoteDataSource.getInstance().submit(mRequest, new PaymentDataSource.PostLoadCallback() {
            @Override
            public void onSuccess() {
                mView.showSuccessMessage();
            }

            @Override
            public void onError(String message) {
                mView.showErrorMessage(message, () -> submit(receipt, amount));
            }
        });
    }
}
