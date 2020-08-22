package com.noscale.edelweiss.data.source.remote.wp;

import com.noscale.edelweiss.data.source.remote.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @DELETE("package/{id}")
    Call<BaseResponse> delete (@Path("id") int id);

    @PUT("package-detail")
    Call<BaseResponse> edit (@Body PackageEditRequest request);
}
