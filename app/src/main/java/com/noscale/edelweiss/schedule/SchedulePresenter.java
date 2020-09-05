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

    private List<Schedule> mSchedules;

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
                mSchedules = schedules;
                mView.showPage(mSchedules);
            }

            @Override
            public void onLoadScheduleFailure(String message) {
                mView.showErrorMessage(message);
            }
        });
    }

    @Override
    public String getScheduleContent() {
        String content = "";

        for (Schedule  s : mSchedules) {
            if (null == s) continue;
            content += s.getName() + "\n" + s.getBookingNumber() + "\n" + s.getDateTime() + "\n" + s.getStatus().name() + "\n\n";
        }

        return content;
    }

    @Override
    public void update(int id, String status) {
        ScheduleRemoteDataSource.getInstance().update(id, status, new ScheduleDataSource.PostStatusUpdateCallback() {
            @Override
            public void onSuccessUpdate() {
                fetch();
            }

            @Override
            public void onErrorUpdate(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
