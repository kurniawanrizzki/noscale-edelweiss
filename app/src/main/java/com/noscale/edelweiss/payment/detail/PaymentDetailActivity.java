package com.noscale.edelweiss.payment.detail;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.data.Payment;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PaymentDetailActivity extends BaseActivity {

    public static final String PAYMENT_BOOKING_NUMBER_ARG = "PAYMENT_BOOKING_NUMBER_ARG";

    public static final String PAYMENT_LIST_ARG = "PAYMENT_LIST_ARG";

    @Override
    protected void init(Bundle savedInstanceState) {

        List<Payment> payments = new ArrayList<>();

        String bookingNumber = null;

        if (getIntent().hasExtra(PAYMENT_LIST_ARG)) {
            payments = getIntent().getParcelableArrayListExtra(PAYMENT_LIST_ARG);
        }

        if (getIntent().hasExtra(PAYMENT_BOOKING_NUMBER_ARG)) {
            bookingNumber = getIntent().getStringExtra(PAYMENT_BOOKING_NUMBER_ARG);
        }

        mFragment = PaymentDetailFragment.newInstance(
                payments,
                bookingNumber
        );

        mPresenter = new PaymentDetailPresenter(
                (PaymentDetailContract.View) mFragment
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.payment_txt;
    }
}
