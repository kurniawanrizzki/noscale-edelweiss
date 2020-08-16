package com.noscale.edelweiss.registration;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public interface RegistrationContract {
    interface View extends BaseView<BasePresenter> {
        void goToLogin ();
        void showSuccessfulSignUp ();
        void showFailureSignUp(String message);
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void signUp (String firstName, String lastName, String email, String password);
    }
}
