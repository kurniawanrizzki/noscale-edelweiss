package com.noscale.edelweiss.login;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.common.configuration.AppConfiguration;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mFragment = LoginFragment.newInstance();
        mPresenter = new LoginPresenter(
                (LoginContract.View) mFragment,
                AppConfiguration.getInstance(this)
        );
    }

}
