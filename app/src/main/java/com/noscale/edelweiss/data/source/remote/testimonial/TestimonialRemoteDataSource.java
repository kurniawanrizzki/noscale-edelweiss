package com.noscale.edelweiss.data.source.remote.testimonial;

import com.noscale.edelweiss.data.Testimonial;
import com.noscale.edelweiss.data.source.TestimonialDataSource;
import com.noscale.edelweiss.data.source.remote.APIService;
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

                if ((null != res) && res.isOk()) {
                    List<Testimonial> testimonials = res.getTestimonials();
                    callback.onLoadTestimonial(testimonials);
                    return;
                }

                callback.onEmptyTestimonial();
            }

            @Override
            public void onFailure(Call<TestimonialResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onLoadTestimonialFailure(message);
            }
        });
    }
}
