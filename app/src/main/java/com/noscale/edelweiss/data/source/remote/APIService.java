package com.noscale.edelweiss.data.source.remote;

import com.noscale.edelweiss.data.source.remote.authentication.AuthenticationAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class APIService {

    private static APIService instance;

    private static final String BASE_URL = "http://192.168.43.254:6666/edelwish-service/v1/";

    private AuthenticationAPI mAuthenticationApi;

    public static APIService getInstance() {
        if (null == instance) instance = new APIService();
        return instance;
    }

    public APIService() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAuthenticationApi = client.create(AuthenticationAPI.class);
    }

    public AuthenticationAPI getAuthenticationApi () {
        return mAuthenticationApi;
    }
}
