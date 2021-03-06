package com.noscale.edelweiss.registration;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class RegistrationActivity extends BaseActivity {

    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = RegistrationFragment.newInstance();

        mPresenter = new RegistrationPresenter(
                (RegistrationContract.View) mFragment
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.sign_up_txt;
    }
}
