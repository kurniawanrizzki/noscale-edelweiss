package com.noscale.edelweiss.data.source.remote.buffet;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public interface BuffetAPI {
    @GET("list-buffet")
    Call<BuffetTypeResponse> getBuffetTypes ();
}
