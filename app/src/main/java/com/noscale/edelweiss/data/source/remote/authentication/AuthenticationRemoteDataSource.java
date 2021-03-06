package com.noscale.edelweiss.data.source.remote.authentication;

import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.data.source.AuthenticationDataSource;
import com.noscale.edelweiss.data.source.remote.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class AuthenticationRemoteDataSource implements AuthenticationDataSource {

    private APIService mService;

    private static AuthenticationRemoteDataSource instance;

    public static AuthenticationRemoteDataSource getInstance () {
        if (null == instance) instance = new AuthenticationRemoteDataSource();
        return instance;
    }

    public AuthenticationRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void signIn(String email, String password, SignInCallback callback) {
        SignInRequest request = new SignInRequest(email, password);

        Call<SignInResponse> response = mService.getAuthenticationApi().signIn(request);
        response.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                SignInResponse res = response.body();

                if ((null != res) && res.isOk()) {
                    User user = res.getUser();
                    callback.onSignInSuccess(user);
                    return;
                }

                callback.onSignInFailure();
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onSignInError(message);
            }
        });
    }

    @Override
    public void signUp(String firstName, String lastName, String email, String password, SignUpCallback callback) {
        SignUpRequest request = new SignUpRequest(firstName, lastName, email, password);

        Call<SignUpResponse> response = mService.getAuthenticationApi().signUp(request);
        response.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse res = response.body();

                if ((null != res) && res.isOk()) {
                    callback.onSignUpSuccess();
                    return;
                }

                callback.onSignUpFailure(res.getStatusMessage());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onSignUpError(message);
            }
        });

    }

}
