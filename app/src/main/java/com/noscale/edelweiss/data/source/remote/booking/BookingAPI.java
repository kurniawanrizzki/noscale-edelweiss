package com.noscale.edelweiss.data.source.remote.booking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface BookingAPI {
    @POST("booking")
    Call<BookingSubmissionResponse> submit (@Body BookingSubmissionRequest request);

    @GET("package-building/{packageId}")
    Call<BuildingResponse> getBuildingListByPackageId (@Path("packageId") int packageId);
}
