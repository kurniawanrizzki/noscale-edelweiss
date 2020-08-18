package com.noscale.edelweiss.wp.creation;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class WeddingPackageCreationActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = WeddingPackageCreationFragment.newInstance();
        mPresenter = new WeddingPackageCreationPresenter(
                (WeddingPackageCreationContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }
}
