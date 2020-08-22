package com.noscale.edelweiss.wp.details;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.data.WeddingPackage;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class WeddingPackageDetailActivity extends BaseActivity {

    public static final String DETAIL_ARG = "DETAIL_ARG";

    @Override
    protected void init(Bundle savedInstanceState) {
        WeddingPackage wp = null;

        if (getIntent().hasExtra(DETAIL_ARG)) {
            wp = getIntent().getParcelableExtra(DETAIL_ARG);
        }

        mFragment = WeddingPackageDetailFragment.newInstance(wp);
        mPresenter = new WeddingPackageDetailPresenter(
                (WeddingPackageDetailContract.View) mFragment
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.package_txt;
    }
}
