package com.noscale.edelweiss.dashboard;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class DashboardActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = DashboardFragment.newInstance();

        mPresenter = new DashboardPresenter(
                (DashboardContract.View) mFragment
        );
    }

    @Override
    protected int getResContentView() {
        return R.layout.activity_base_no_action_bar;
    }

    @Override
    protected int getActivityTitle() {
        return 0;
    }
}
