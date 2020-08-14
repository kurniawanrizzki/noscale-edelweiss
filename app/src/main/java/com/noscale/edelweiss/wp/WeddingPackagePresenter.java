package com.noscale.edelweiss.wp;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackagePresenter implements WeddingPackageContract.Presenter {

    private WeddingPackageContract.View mView;

    private boolean isDataMissing;

    public WeddingPackagePresenter (WeddingPackageContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.isDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!isDataMissing) return;
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }
}
