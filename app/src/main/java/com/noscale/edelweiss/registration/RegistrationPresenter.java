package com.noscale.edelweiss.registration;

import com.noscale.edelweiss.data.source.AuthenticationDataSource;
import com.noscale.edelweiss.data.source.remote.authentication.AuthenticationRemoteDataSource;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View mView;

    public RegistrationPresenter (RegistrationContract.View view) {
        view.setPresenter(this);

        this.mView = view;
    }

    @Override
    public void start() {
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    @Override
    public void signUp(String firstName, String lastName, String email, String password) {
        AuthenticationRemoteDataSource.getInstance().signUp(firstName, lastName, email, password, new AuthenticationDataSource.SignUpCallback() {
            @Override
            public void onSignUpSuccess() {
                mView.showSuccessfulSignUp();
            }

            @Override
            public void onSignUpFailure(String message) {
                mView.showFailureSignUp(message);
            }

            @Override
            public void onSignUpError(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
