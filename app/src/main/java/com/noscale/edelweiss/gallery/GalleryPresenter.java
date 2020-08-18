package com.noscale.edelweiss.gallery;

import com.noscale.edelweiss.data.Gallery;
import com.noscale.edelweiss.data.source.GalleryDataSource;
import com.noscale.edelweiss.data.source.remote.gallery.GalleryRemoteDataSource;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class GalleryPresenter implements GalleryContract.Presenter {

    private GalleryContract.View mView;

    private boolean isDataMissing;

    public GalleryPresenter (GalleryContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.isDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!isDataMissing) return;

        fetch();
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }

    @Override
    public void fetch() {
        GalleryRemoteDataSource.getInstance().getList(new GalleryDataSource.GetLoadCallback() {
            @Override
            public void onLoadGallery(List<Gallery> galleries) {
                mView.append(galleries);
            }

            @Override
            public void onErrorLoadGallery(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
