package com.noscale.edelweiss.data.source.remote.payment;

import com.noscale.edelweiss.data.source.remote.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface PaymentAPI {
    @GET("list-payment/{userId}")
    Call<PaymentTypeResponse> getPaymentTypes (@Path("userId") int userId);

    @POST("payment")
    Call<BaseResponse> submit (@Body PaymentSubmissionRequest request);

}
