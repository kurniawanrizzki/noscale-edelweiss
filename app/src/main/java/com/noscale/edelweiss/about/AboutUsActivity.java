package com.noscale.edelweiss.about;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class AboutUsActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = AboutUsFragment.newInstance();
    }
}
