package com.noscale.edelweiss.gallery;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class GalleryPresenter implements GalleryContract.Presenter {

    private GalleryContract.View mView;

    private boolean mIsDataMissing;

    public GalleryPresenter (GalleryContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        mView = view;
        mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!mIsDataMissing) {
            mView.showProgressView(false);
            return;
        }

        mView.showProgressView(false);
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
