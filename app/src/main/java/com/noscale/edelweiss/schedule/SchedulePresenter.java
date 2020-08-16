package com.noscale.edelweiss.schedule;

import com.noscale.edelweiss.data.Schedule;
import com.noscale.edelweiss.data.source.ScheduleDataSource;
import com.noscale.edelweiss.data.source.remote.schedule.ScheduleRemoteDataSource;
import java.util.List;

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

        fetch();
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }

    @Override
    public void fetch() {
        ScheduleRemoteDataSource.getInstance().getList(new ScheduleDataSource.GetLoadCallback() {
            @Override
            public void onLoadSchedule(List<Schedule> schedules) {
                mView.showPage(schedules);
            }

            @Override
            public void onEmptySchedule() {
                mView.showEmptyPage();
            }

            @Override
            public void onLoadScheduleFailure(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
