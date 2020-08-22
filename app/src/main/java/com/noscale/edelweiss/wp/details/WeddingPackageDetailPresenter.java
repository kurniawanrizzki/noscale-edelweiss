package com.noscale.edelweiss.wp.details;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class WeddingPackageDetailPresenter implements WeddingPackageDetailContract.Presenter {

    private WeddingPackageDetailContract.View mView;

    public WeddingPackageDetailPresenter (WeddingPackageDetailContract.View view) {
        view.setPresenter(this);

        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
