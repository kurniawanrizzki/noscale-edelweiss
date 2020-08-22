package com.noscale.edelweiss.data.source.remote.schedule;

import com.noscale.edelweiss.data.source.remote.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface ScheduleAPI {
    @GET("schedule")
    Call<ScheduleResponse> getSchedules ();

    @PUT("booking")
    Call<BaseResponse> updateStatus (@Body ScheduleUpdateRequest request);
}
