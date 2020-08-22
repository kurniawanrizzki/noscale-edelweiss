package com.noscale.edelweiss.login;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void goToRegistration ();
        void goToDashboard ();
        void showFailureLogin();
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void signIn (String email, String password);
    }
}
