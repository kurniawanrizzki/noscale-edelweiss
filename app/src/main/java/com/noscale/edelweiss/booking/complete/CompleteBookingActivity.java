package com.noscale.edelweiss.booking.complete;

import android.app.Activity;
import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompleteBookingActivity extends BaseActivity {

    public static final int COMPLETE_BOOKING_REQUEST_CODE = 500;

    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = CompleteBookingFragment.newInstance();
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
        setResult(Activity.RESULT_OK);
        finish();
    }
}
