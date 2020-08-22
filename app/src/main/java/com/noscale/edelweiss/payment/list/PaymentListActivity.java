package com.noscale.edelweiss.payment.list;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PaymentListActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = PaymentListFragment.newInstance();

        mPresenter = new PaymentListPresenter (
                (PaymentListContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.payment_txt;
    }
}
