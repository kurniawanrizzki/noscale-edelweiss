package com.noscale.edelweiss.schedule;

import android.os.Bundle;

import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class ScheduleActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = ScheduleFragment.newInstance();

        mPresenter = new SchedulePresenter(
                (ScheduleContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }
}
