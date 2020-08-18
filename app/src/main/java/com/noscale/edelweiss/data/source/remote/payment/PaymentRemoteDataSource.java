package com.noscale.edelweiss.data.source.remote.payment;

import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.source.PaymentDataSource;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.data.source.remote.APIService;
import com.noscale.edelweiss.data.source.remote.BaseResponse;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class PaymentRemoteDataSource implements PaymentDataSource {

    private APIService mService;

    private static PaymentRemoteDataSource instance;

    public static PaymentRemoteDataSource getInstance() {
        if (null == instance) instance = new PaymentRemoteDataSource();
        return instance;
    }

    public PaymentRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void getPaymentTypes(int userId, GetLoadCallback callback) {
        Call<PaymentTypeResponse> response = mService.getPaymentApi().getPaymentTypes(userId);
        response.enqueue(new Callback<PaymentTypeResponse>() {
            @Override
            public void onResponse(Call<PaymentTypeResponse> call, Response<PaymentTypeResponse> response) {
                PaymentTypeResponse res = response.body();

                if ((null != res) && res.isOk()) {
                    List<PaymentType> types = res.getTypes();
                    List<Booking> bookings = res.getBookings();

                    callback.onLoadPaymentType(types, bookings);
                }
            }

            @Override
            public void onFailure(Call<PaymentTypeResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onFailurePaymentType(message);
            }
        });
    }

    @Override
    public void submit(PaymentSubmissionRequest request, PostLoadCallback callback) {
        Call<BaseResponse> response = mService.getPaymentApi().submit(request);
        response.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    BaseResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        callback.onSuccess();
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
