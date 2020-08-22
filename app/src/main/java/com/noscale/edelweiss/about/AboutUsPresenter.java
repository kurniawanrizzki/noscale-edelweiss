package com.noscale.edelweiss.about;

import android.content.res.Resources;

import com.noscale.edelweiss.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 21/08/20.
 */
public class AboutUsPresenter implements AboutUsContract.Presenter {

    private AboutUsContract.View mView;

    private boolean mIsDataMissing;

    private Resources mRes;

    public AboutUsPresenter (AboutUsContract.View view, Resources res, boolean isDataMissing) {
        view.setPresenter(this);

        mView = view;
        mRes = res;
        mIsDataMissing = isDataMissing;
    }

    @Override
    public void loadContent() {
        try {
            InputStream is = mRes.openRawResource(R.raw.about_us);
            byte[] buffer = new byte[is.available()];

            while (is.read(buffer) != -1);
            String content= new String(buffer);

            mView.append(content);
        } catch (IOException e) {
            e.printStackTrace();
            mView.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public void start() {
        if (!mIsDataMissing) return;

        loadContent();
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
