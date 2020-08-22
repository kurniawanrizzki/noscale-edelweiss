package com.noscale.edelweiss.data.source.remote.gallery;

import com.noscale.edelweiss.data.Gallery;
import com.noscale.edelweiss.data.source.GalleryDataSource;
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
 * Created by kurniawanrizzki on 18/08/20.
 */
public class GalleryRemoteDataSource implements GalleryDataSource {

    private APIService mService;

    private static GalleryRemoteDataSource instance;

    public static GalleryRemoteDataSource getInstance() {
        if (null == instance) instance = new GalleryRemoteDataSource();
        return instance;
    }

    public GalleryRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void getList(GetLoadCallback callback) {
        Call<GalleryResponse> response = mService.getGalleryApi().getGalleries();
        response.enqueue(new Callback<GalleryResponse>() {
            @Override
            public void onResponse(Call<GalleryResponse> call, Response<GalleryResponse> response) {
                GalleryResponse res = response.body();
                List<Gallery> galleries = new ArrayList<>();
                if ((null != res) && res.isOk()) {
                    galleries = res.getGalleries();
                }

                callback.onLoadGallery(galleries);
            }

            @Override
            public void onFailure(Call<GalleryResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onErrorLoadGallery(message);
            }
        });
    }

    @Override
    public void submit(GallerySubmissionRequest request, PostCallback callback) {
        Call<BaseResponse> response = mService.getGalleryApi().submit(request);
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
