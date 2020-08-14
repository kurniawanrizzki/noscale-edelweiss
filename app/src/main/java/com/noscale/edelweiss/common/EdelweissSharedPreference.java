package com.noscale.edelweiss.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class EdelweissSharedPreference {

    private Context mContext;

    private SharedPreferences mPreferences;

    private SharedPreferences.Editor mPreferenceEditor;

    private static final String EDELWEISS_APP = "EDELWEISS_APP";

    public EdelweissSharedPreference (Context context) {
        this.mContext = context;

        init();
    }

    private void init () {
        this.mPreferences = mContext.getSharedPreferences(EDELWEISS_APP, Context.MODE_PRIVATE);
        this.mPreferenceEditor = mPreferences.edit();
    }

    public void putString (String key, String value) {
        mPreferenceEditor.putString(key, value).commit();
    }

    public String getString (String key, String df) {
        return mPreferences.getString(key, df);
    }

    public void putInteger (String key, int value) {
        mPreferenceEditor.putInt(key, value).commit();
    }

    public int getInteger (String key, int df) {
        return mPreferences.getInt(key, df);
    }

    public void putBoolean (String key, boolean value) {
        mPreferenceEditor.putBoolean(key, value).commit();
    }

    public boolean getBoolean (String key, boolean df) {
        return mPreferences.getBoolean(key, df);
    }
}
