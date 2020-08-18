package com.noscale.edelweiss.data.source.remote.wp;

import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import com.noscale.edelweiss.data.source.PackageDataSource;
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
 * Created by kurniawanrizzki on 17/08/20.
 */
public class PackageRemoteDataSource implements PackageDataSource {

    private APIService mService;

    private static PackageRemoteDataSource instance;

    public static PackageRemoteDataSource getInstance() {
        if (null == instance) instance = new PackageRemoteDataSource();
        return instance;
    }

    public PackageRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void getList(GetLoadCallback callback) {
        Call<PackageListResponse> response = mService.getPackageApi().getListPackage();
        response.enqueue(new Callback<PackageListResponse>() {
            @Override
            public void onResponse(Call<PackageListResponse> call, Response<PackageListResponse> response) {
                PackageListResponse res = response.body();

                if ((null != res) && res.isOk()) {
                    List<WeddingPackage> data = res.getPackages();
                    callback.onLoadWeddingPackages(data);
                    return;
                }

                callback.onLoadWeddingPackages(new ArrayList<>());
            }

            @Override
            public void onFailure(Call<PackageListResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onFailureWeddingPackages(message);
            }
        });
    }

    @Override
    public void getPackageDetails(GetLoadPackageDetailsCallback callback) {
        Call<PackageDetailListResponse> response = mService.getPackageApi().getListDetailPackage();
        response.enqueue(new Callback<PackageDetailListResponse>() {
            @Override
            public void onResponse(Call<PackageDetailListResponse> call, Response<PackageDetailListResponse> response) {
                try {
                    PackageDetailListResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        List<WeddingPackageDetail> details = res.getDetails();
                        callback.onLoadPackageDetails(details);
                        return;
                    }

                    String message = response.errorBody().string();
                    callback.onErrorPackageDetails(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PackageDetailListResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onErrorPackageDetails(message);
            }
        });
    }

    @Override
    public void submit(PackageSubmissionRequest request, PostCallback callback) {
        Call<BaseResponse> response = mService.getPackageApi().submit(request);
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
