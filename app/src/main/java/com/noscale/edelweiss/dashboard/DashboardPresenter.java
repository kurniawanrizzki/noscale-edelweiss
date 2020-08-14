package com.noscale.edelweiss.dashboard;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View mView;

    private boolean isDataMissing;

    public DashboardPresenter (DashboardContract.View view, boolean isDataMissing) {
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
