package com.noscale.edelweiss.booking;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingActivity extends BaseActivity {

    public static final int BOOKING_REQUEST_CODE = 600;

    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = BookingFragment.newInstance();

        mPresenter = new BookingPresenter(
                (BookingContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.booking_txt;
    }
}
