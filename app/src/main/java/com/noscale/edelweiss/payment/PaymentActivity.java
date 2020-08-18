package com.noscale.edelweiss.payment;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.common.configuration.AppConfiguration;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class PaymentActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = PaymentFragment.newInstance();

        mPresenter = new PaymentPresenter(
                (PaymentContract.View) mFragment,
                AppConfiguration.getInstance(this),
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }
}
