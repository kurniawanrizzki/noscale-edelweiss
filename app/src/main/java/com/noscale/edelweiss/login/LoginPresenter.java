package com.noscale.edelweiss.login;

import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.data.source.AuthenticationDataSource;
import com.noscale.edelweiss.data.source.remote.authentication.AuthenticationRemoteDataSource;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    private AppConfiguration mConfiguration;

    public LoginPresenter (LoginContract.View view, AppConfiguration configuration) {
        view.setPresenter(this);

        this.mView = view;
        this.mConfiguration = configuration;
    }

    @Override
    public void start() {

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    @Override
    public void signIn(String email, String password) {
        AuthenticationRemoteDataSource.getInstance().signIn(email, password, new AuthenticationDataSource.SignInCallback() {
            @Override
            public void onSignInSuccess(User user) {
                mConfiguration.setAuthenticated(true);
                mConfiguration.setUserId(user.getId());

                mView.goToDashboard();
            }

            @Override
            public void onSignInFailure() {
                mView.showFailureLogin();
            }

            @Override
            public void onSignInError(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
