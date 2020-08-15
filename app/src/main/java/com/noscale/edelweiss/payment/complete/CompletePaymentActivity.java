package com.noscale.edelweiss.payment.complete;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompletePaymentActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = CompletePaymentFragment.newInstance();
    }
}
