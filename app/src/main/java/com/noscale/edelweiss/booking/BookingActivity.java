package com.noscale.edelweiss.booking;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = BookingFragment.newInstance();

        mPresenter = new BookingPresenter(
                (BookingContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }
}
