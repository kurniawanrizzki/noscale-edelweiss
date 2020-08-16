package com.noscale.edelweiss.data.source.remote.authentication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface AuthenticationAPI {
    @POST("login")
    Call<SignInResponse> signIn (@Body SignInRequest request);
}
