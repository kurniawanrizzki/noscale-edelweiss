package com.noscale.edelweiss.data.source.remote.buffet;

import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.source.remote.APIService;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import com.noscale.edelweiss.data.source.remote.BuffetDataSource;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class BuffetRemoteDataSource implements BuffetDataSource {

    private APIService mService;

    private static BuffetRemoteDataSource instance;

    public static BuffetRemoteDataSource getInstance () {
        if (null == instance) instance = new BuffetRemoteDataSource();
        return instance;
    }

    public BuffetRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void getList(GetLoadCallback callback) {
        Call<BuffetTypeResponse> response = mService.getBuffetApi().getBuffetTypes();
        response.enqueue(new Callback<BuffetTypeResponse>() {
            @Override
            public void onResponse(Call<BuffetTypeResponse> call, Response<BuffetTypeResponse> response) {
                try {
                    BuffetTypeResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        List<BuffetType> buffetTypes = res.getTypes();
                        callback.onLoadBuffetTypes(buffetTypes);
                        return;
                    }

                    String message = response.errorBody().string();
                    callback.onErrorBuffetTypes(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BuffetTypeResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onErrorBuffetTypes(message);
            }
        });
    }

    @Override
    public void editBuffetType(BuffetTypeEditRequest request, PostCallback callback) {
        Call<BaseResponse> response = mService.getBuffetApi().editBuffetType(request);
        response.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    BaseResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        callback.onSuccess();
                        return;
                    }

                    String message = response.errorBody().string();
                    callback.onError(message);
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

    @Override
    public void editBuffetDetails(BuffetDetailsEditRequest request,  PostCallback callback) {
        Call<BaseResponse> response = mService.getBuffetApi().editBuffetDetails(request);
        response.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    BaseResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        callback.onSuccess();
                        return;
                    }

                    String message = response.errorBody().string();
                    callback.onError(message);
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
