package com.noscale.edelweiss.common;

import android.view.View;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class UICommon {

    public static void showProgressView (View mainView, View progressView, boolean isShow) {
        if (isShow) {
            mainView.setVisibility(View.VISIBLE);
            progressView.setVisibility(View.GONE);
            return;
        }

        mainView.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
    }

}
