package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.Schedule;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface ScheduleDataSource {

    interface GetLoadCallback {
        void onLoadSchedule (List<Schedule> schedules);
        void onLoadScheduleFailure (String message);
    }

    interface PostStatusUpdateCallback {
        void onSuccessUpdate ();
        void onErrorUpdate (String message);
    }

    void getList (GetLoadCallback callback);

    void update (int id, String status, PostStatusUpdateCallback callback);
}
