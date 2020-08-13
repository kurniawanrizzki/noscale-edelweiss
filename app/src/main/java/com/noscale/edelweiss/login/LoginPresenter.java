package com.noscale.edelweiss.login;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    private boolean isDataMissing;

    public LoginPresenter (LoginContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.isDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!isDataMissing) return;
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }
}
