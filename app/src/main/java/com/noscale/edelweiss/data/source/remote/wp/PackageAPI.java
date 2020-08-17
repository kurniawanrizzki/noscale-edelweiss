package com.noscale.edelweiss.data.source.remote.wp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface PackageAPI {
    @GET("list-package")
    Call<PackageListResponse> getListPackage ();
}
