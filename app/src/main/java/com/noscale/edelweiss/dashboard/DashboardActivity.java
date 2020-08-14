package com.noscale.edelweiss.dashboard;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class DashboardActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = DashboardFragment.newInstance();

        mPresenter = new DashboardPresenter(
                (DashboardContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }
}
