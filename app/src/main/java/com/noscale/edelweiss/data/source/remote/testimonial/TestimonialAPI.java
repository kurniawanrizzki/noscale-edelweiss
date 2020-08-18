package com.noscale.edelweiss.data.source.remote.testimonial;

import com.noscale.edelweiss.data.source.remote.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface TestimonialAPI {
    @GET("testimoni")
    Call<TestimonialResponse> getTestimonials ();

    @POST("testimoni")
    Call<BaseResponse> submit (@Body TestimonialSubmissionRequest request);
}
