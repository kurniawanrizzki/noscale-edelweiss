package com.noscale.edelweiss.wp;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackageActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = WeddingPackageFragment.newInstance();

        mPresenter = new WeddingPackagePresenter(
                (WeddingPackageContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }
}
