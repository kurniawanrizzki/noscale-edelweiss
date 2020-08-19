package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.Gallery;
import com.noscale.edelweiss.data.source.remote.gallery.GallerySubmissionRequest;

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

    interface PostCallback {
        void onSuccess ();
        void onError (String message);
    }

    void getList (GetLoadCallback callback);

    void submit (GallerySubmissionRequest request, PostCallback callback);
}
