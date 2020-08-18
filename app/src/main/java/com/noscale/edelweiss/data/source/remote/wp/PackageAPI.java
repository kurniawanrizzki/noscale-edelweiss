package com.noscale.edelweiss.data.source.remote.wp;

import com.noscale.edelweiss.data.source.remote.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface PackageAPI {
    @GET("list-package")
    Call<PackageListResponse> getListPackage ();

    @GET("list-detail-package")
    Call<PackageDetailListResponse> getListDetailPackage ();

    @POST("add-package")
    Call<BaseResponse> submit (@Body PackageSubmissionRequest request);
}
