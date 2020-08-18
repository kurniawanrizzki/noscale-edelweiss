package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.Gallery;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public interface GalleryDataSource {

    interface GetLoadCallback {
        void onLoadGallery (List<Gallery> galleries);
        void onErrorLoadGallery (String message);
    }

    void getList (GetLoadCallback callback);
}
