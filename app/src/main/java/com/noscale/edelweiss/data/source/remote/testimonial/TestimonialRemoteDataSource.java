package com.noscale.edelweiss.data.source.remote.testimonial;

import com.noscale.edelweiss.data.Testimonial;
import com.noscale.edelweiss.data.source.TestimonialDataSource;
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
public class TestimonialRemoteDataSource implements TestimonialDataSource {

    private APIService mService;

    private static TestimonialRemoteDataSource instance;

    public static TestimonialRemoteDataSource getInstance() {
        if (null == instance) instance = new TestimonialRemoteDataSource();
        return instance;
    }

    public TestimonialRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void getList(GetLoadCallback callback) {
        Call<TestimonialResponse> response = mService.getTestimonialApi().getTestimonials();
        response.enqueue(new Callback<TestimonialResponse>() {
            @Override
            public void onResponse(Call<TestimonialResponse> call, Response<TestimonialResponse> response) {
                TestimonialResponse res = response.body();
                List<Testimonial> testimonials = new ArrayList<>();

                if ((null != res) && res.isOk()) {
                    testimonials = res.getTestimonials();
                }

                callback.onLoadTestimonial(testimonials);
            }

            @Override
            public void onFailure(Call<TestimonialResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onLoadTestimonialFailure(message);
            }
        });
    }

    @Override
    public void submit(int userId, String testimonial, PostCallback callback) {
        TestimonialSubmissionRequest request = new TestimonialSubmissionRequest(testimonial, userId);
        Call<BaseResponse> response = mService.getTestimonialApi().submit(request);
        response.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    BaseResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        callback.onSuccess();
                        return;
                    }

                    callback.onError(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onError(message);
            }
        });
    }
}
