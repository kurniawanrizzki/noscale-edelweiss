package com.noscale.edelweiss.data.source.remote.gallery;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public interface GalleryAPI {
    @GET("gallery")
    Call<GalleryResponse> getGalleries ();
}
