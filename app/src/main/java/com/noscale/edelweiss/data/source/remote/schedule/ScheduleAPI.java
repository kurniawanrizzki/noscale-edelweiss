package com.noscale.edelweiss.data.source.remote.schedule;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface ScheduleAPI {
    @GET("schedule")
    Call<ScheduleResponse> getSchedules ();
}
