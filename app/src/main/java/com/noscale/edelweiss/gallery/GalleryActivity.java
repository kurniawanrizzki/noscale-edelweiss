package com.noscale.edelweiss.gallery;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class GalleryActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = GalleryFragment.newInstance();
        mPresenter = new GalleryPresenter(
                (GalleryContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.gallery_txt;
    }
}
