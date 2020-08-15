package com.noscale.edelweiss.booking.complete;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompleteBookingActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = CompleteBookingFragment.newInstance();
    }
}
