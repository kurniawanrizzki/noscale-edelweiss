package com.noscale.edelweiss.payment.complete;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.payment.PaymentActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompletePaymentActivity extends BaseActivity {

    public static final int COMPLETE_PAYMENT_REQUEST_CODE = 400;

    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = CompletePaymentFragment.newInstance();
    }

    @Override
    protected int getResContentView() {
        return R.layout.activity_base_no_action_bar;
    }

    @Override
    protected int getActivityTitle() {
        return 0;
    }

    @Override
    public void onBackPressed() {
        setResult(PaymentActivity.RESULT_OK);
        finish();
    }
}
