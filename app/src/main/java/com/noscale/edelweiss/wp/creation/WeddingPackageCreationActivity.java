package com.noscale.edelweiss.wp.creation;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.wp.details.WeddingPackageDetailActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class WeddingPackageCreationActivity extends BaseActivity {

    public static final int WEDDING_PACKAGE_CREATION_REQUEST_CODE = 200;

    public static final String ACTION_EDIT = "ACTION_EDIT";

    private WeddingPackage mWeddingPackage;

    @Override
    protected void init(Bundle savedInstanceState) {

        if (getIntent().hasExtra(WeddingPackageDetailActivity.DETAIL_ARG)) {
            mWeddingPackage = getIntent().getParcelableExtra(WeddingPackageDetailActivity.DETAIL_ARG);
        }

        mFragment = WeddingPackageCreationFragment.newInstance();
        mPresenter = new WeddingPackageCreationPresenter(
                (WeddingPackageCreationContract.View) mFragment,
                mWeddingPackage,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.package_txt;
    }
}
