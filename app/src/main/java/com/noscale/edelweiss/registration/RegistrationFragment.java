package com.noscale.edelweiss.registration;

import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class RegistrationFragment extends BaseFragment implements RegistrationContract.View {

    public static RegistrationFragment newInstance () {
        return new RegistrationFragment();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_registration;
    }
}
