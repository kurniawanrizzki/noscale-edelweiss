package com.noscale.edelweiss.schedule;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Schedule;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public interface ScheduleContract {
    interface View extends BaseView<Presenter> {
        void showPage (List<Schedule> schedules);
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void fetch ();
        String getScheduleContent ();
        void update (int id, String status);
    }
}
