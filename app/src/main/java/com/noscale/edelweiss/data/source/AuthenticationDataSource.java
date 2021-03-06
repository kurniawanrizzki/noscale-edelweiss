package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.User;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface AuthenticationDataSource {
    interface SignInCallback {
        void onSignInSuccess(User user);
        void onSignInFailure ();
        void onSignInError(String message);
    }

    interface SignUpCallback {
        void onSignUpSuccess ();
        void onSignUpFailure (String message);
        void onSignUpError (String message);
    }

    void signIn (String email, String password, SignInCallback callback);

    void signUp (String firstName, String lastName, String email, String password, SignUpCallback callback);
}
