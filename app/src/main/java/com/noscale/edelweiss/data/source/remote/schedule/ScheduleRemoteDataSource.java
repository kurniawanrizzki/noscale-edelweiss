package com.noscale.edelweiss.data.source.remote.schedule;

import com.noscale.edelweiss.data.Schedule;
import com.noscale.edelweiss.data.source.ScheduleDataSource;
import com.noscale.edelweiss.data.source.remote.APIService;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
                List<Schedule> schedules = new ArrayList<>();

                if ((null != res) && res.isOk()) {
                    schedules = res.getSchedules();
                }

                callback.onLoadSchedule(schedules);
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onLoadScheduleFailure(message);
            }
        });
    }

    @Override
    public void update(int id, String status, PostStatusUpdateCallback callback) {
        ScheduleUpdateRequest request = new ScheduleUpdateRequest(id, status);
        Call<BaseResponse> response = mService.getScheduleApi().updateStatus(request);
        response.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    BaseResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        callback.onSuccessUpdate();
                        return;
                    }

                    String message = response.errorBody().string();
                    callback.onErrorUpdate(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onErrorUpdate(message);
            }
        });
    }
}
