package com.noscale.edelweiss.about;

import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class AboutUsFragment extends BaseFragment implements AboutUsContract.View {

    public static AboutUsFragment newInstance () {
        return new AboutUsFragment();
    }

    @Override
    public void setPresenter(AboutUsContract.Presenter presenter) {

    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_about_us;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }
}
