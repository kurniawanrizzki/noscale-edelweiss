package com.noscale.edelweiss.schedule;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class SchedulePresenter implements ScheduleContract.Presenter {

    private ScheduleContract.View mView;

    private boolean isDataMissing;

    public SchedulePresenter (ScheduleContract.View view, boolean isDataMissing) {
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
