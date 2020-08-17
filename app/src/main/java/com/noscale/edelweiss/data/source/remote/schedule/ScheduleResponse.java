package com.noscale.edelweiss.data.source.remote.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.Schedule;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class ScheduleResponse extends BaseResponse {

    @SerializedName("bookingList")
    @Expose
    private List<Schedule> schedules;

    public List<Schedule> getSchedules () {
        return schedules;
    }

}
