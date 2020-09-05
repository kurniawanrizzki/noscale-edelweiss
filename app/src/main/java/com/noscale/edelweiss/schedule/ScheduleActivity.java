package com.noscale.edelweiss.schedule;

import android.os.Bundle;

import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class ScheduleActivity extends BaseActivity {

    public static final int EXPORT_SCHEDULE_REQUEST_CODE = 1111;

    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = ScheduleFragment.newInstance();

        mPresenter = new SchedulePresenter(
                (ScheduleContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.schedule_txt;
    }
}
