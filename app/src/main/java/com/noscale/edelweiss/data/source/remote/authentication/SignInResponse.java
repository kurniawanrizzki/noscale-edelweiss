package com.noscale.edelweiss.data.source.remote.authentication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.data.source.remote.BaseResponse;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class SignInResponse extends BaseResponse {

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }
}
