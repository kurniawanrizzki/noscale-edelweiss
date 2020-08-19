package com.noscale.edelweiss.gallery.creation;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class GalleryCreationActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = GalleryCreationFragment.newInstance();
        mPresenter = new GalleryCreationPresenter(
                (GalleryCreationContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }
}
