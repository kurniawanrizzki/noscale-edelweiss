package com.noscale.edelweiss.data.source.remote.schedule;

import com.noscale.edelweiss.data.source.ScheduleDataSource;
import com.noscale.edelweiss.data.source.remote.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class ScheduleRemoteDataSource implements ScheduleDataSource {

    private APIService mService;

    private static ScheduleRemoteDataSource instance;

    public static ScheduleRemoteDataSource getInstance() {
        if (null == instance) instance = new ScheduleRemoteDataSource();
        return instance;
    }

    public ScheduleRemoteDataSource () {
        mService = APIService.getInstance();
    }

    @Override
    public void getList(GetLoadCallback callback) {
        Call<ScheduleResponse> request = mService.getScheduleApi().getSchedules();
        request.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                ScheduleResponse res = response.body();

                if ((null != res) && res.isOk()) {
                    callback.onLoadSchedule(res.getSchedules());
                    return;
                }

                callback.onEmptySchedule();
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onLoadScheduleFailure(message);
            }
        });
    }
}
