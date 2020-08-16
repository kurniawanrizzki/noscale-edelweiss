package com.noscale.edelweiss.data.source.remote.wp;

import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.source.PackageDataSource;
import com.noscale.edelweiss.data.source.remote.APIService;
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
                }
            }

            @Override
            public void onFailure(Call<PackageListResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onFailureWeddingPackages(message);
            }
        });
    }
}
