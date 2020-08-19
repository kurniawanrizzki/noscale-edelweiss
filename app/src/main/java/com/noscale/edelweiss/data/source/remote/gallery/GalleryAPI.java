package com.noscale.edelweiss.data.source.remote.gallery;

import com.noscale.edelweiss.data.source.remote.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public interface GalleryAPI {
    @GET("gallery")
    Call<GalleryResponse> getGalleries ();

    @POST("upload-photo")
    Call<BaseResponse> submit (@Body GallerySubmissionRequest request);
}
