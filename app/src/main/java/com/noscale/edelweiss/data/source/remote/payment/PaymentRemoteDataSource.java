package com.noscale.edelweiss.data.source.remote.payment;

import com.noscale.edelweiss.data.source.PaymentDataSource;
import com.noscale.edelweiss.data.PaymentType;
import com.noscale.edelweiss.data.source.remote.APIService;
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
    public void getPaymentTypes(GetLoadCallback callback) {
        Call<PaymentTypeResponse> response = mService.getPaymentApi().getPaymentTypes();
        response.enqueue(new Callback<PaymentTypeResponse>() {
            @Override
            public void onResponse(Call<PaymentTypeResponse> call, Response<PaymentTypeResponse> response) {
                PaymentTypeResponse res = response.body();

                if ((null != res) && res.isOk()) {
                    List<PaymentType> types = res.getTypes();
                    callback.onLoadPaymentType(types);
                }
            }

            @Override
            public void onFailure(Call<PaymentTypeResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onFailurePaymentType(message);
            }
        });
    }
}
