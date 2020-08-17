package com.noscale.edelweiss.common.configuration;

import android.content.Context;
import com.noscale.edelweiss.common.EdelweissSharedPreference;

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

    public void setUserId (int userId) {
        mPreference.putInteger(AUTHENTICATED_ID_KEY, userId);
    }

    public boolean isAuthenticated () {
        return mPreference.getBoolean(IS_AUTHENTICATED_KEY, false);
    }

    public int getUserId () {
        return mPreference.getInteger(AUTHENTICATED_ID_KEY, 0);
    }
}
