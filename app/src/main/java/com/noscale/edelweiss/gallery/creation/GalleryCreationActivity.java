package com.noscale.edelweiss.gallery.creation;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class GalleryCreationActivity extends BaseActivity {

    public static final int GALLERY_CREATION_REQUEST_CODE = 100;

    public static final int GALLERY_PICK_REQUEST_CODE = 101;

    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = GalleryCreationFragment.newInstance();
        mPresenter = new GalleryCreationPresenter(
                (GalleryCreationContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.gallery_txt;
    }
}
