package com.noscale.edelweiss.common.configuration;

import android.content.Context;
import com.noscale.edelweiss.common.EdelweissSharedPreference;
import com.noscale.edelweiss.data.User;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class AppConfiguration {

    private static AppConfiguration instance;

    private Context mContext;

    private EdelweissSharedPreference mPreference;

    private static final String IS_AUTHENTICATED_KEY = "IS_AUTHENTICATED_KEY";

    private static final String AUTHENTICATED_ID_KEY = "AUTHENTICATED_ID_KEY";

    private static final String AUTHENTICATED_USER_NAME_KEY = "AUTHENTICATED_USER_NAME_KEY";

    private static final String AUTHENTICATED_USER_TYPE_KEY = "AUTHENTICATED_USER_TYPE_KEY";

    protected AppConfiguration(Context context) {
        this.mContext = context;

        init();
    }

    public static AppConfiguration getInstance(Context context) {
        if (null == instance) {
            instance = new AppConfiguration(context);
        }

        return instance;
    }

    private void init () {
        mPreference = new EdelweissSharedPreference(mContext);
    }

    public void setAuthenticated (boolean isAuthenticated) {
        mPreference.putBoolean(IS_AUTHENTICATED_KEY, isAuthenticated);
    }

    public void setAuthenticatedId(int authenticatedId) {
        mPreference.putInteger(AUTHENTICATED_ID_KEY, authenticatedId);
    }

    public boolean isAuthenticated () {
        return mPreference.getBoolean(IS_AUTHENTICATED_KEY, false);
    }

    public int getAuthenticatedId() {
        return mPreference.getInteger(AUTHENTICATED_ID_KEY, 0);
    }

    public void setAuthenticatedUserName (String fullName) {
        mPreference.putString(AUTHENTICATED_USER_NAME_KEY, fullName);
    }

    public String getAuthenticatedUserName () {
        return mPreference.getString(AUTHENTICATED_USER_NAME_KEY, "");
    }

    public void setAuthenticatedUserType (int type) {
        mPreference.putInteger(AUTHENTICATED_USER_TYPE_KEY, type);
    }

    public User.Type getAuthenticatedUserType ()  {
        int type = mPreference.getInteger(AUTHENTICATED_USER_TYPE_KEY, 1);

        for (User.Type t : User.Type.values()) {
            if (type == t.getCode()) return t;
        }

        return User.Type.DEFAULT;
    }
}
