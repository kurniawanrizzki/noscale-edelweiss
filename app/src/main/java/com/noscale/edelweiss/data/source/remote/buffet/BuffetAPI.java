package com.noscale.edelweiss.data.source.remote.buffet;

import com.noscale.edelweiss.data.source.remote.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public interface BuffetAPI {
    @GET("list-buffet")
    Call<BuffetTypeResponse> getBuffetTypes ();

    @PUT("package-buffet")
    Call<BaseResponse> editBuffetType (@Body BuffetTypeEditRequest request);

    @PUT("package-detail-buffet")
    Call<BaseResponse> editBuffetDetails (@Body BuffetDetailsEditRequest request);
}
