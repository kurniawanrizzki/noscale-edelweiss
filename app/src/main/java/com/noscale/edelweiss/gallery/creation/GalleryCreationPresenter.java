package com.noscale.edelweiss.gallery.creation;

import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.source.CategoryDataSource;
import com.noscale.edelweiss.data.source.GalleryDataSource;
import com.noscale.edelweiss.data.source.remote.category.CategoryRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.gallery.GalleryRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.gallery.GallerySubmissionRequest;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class GalleryCreationPresenter implements GalleryCreationContract.Presenter {

    private GalleryCreationContract.View mView;

    private final GallerySubmissionRequest mRequest = new GallerySubmissionRequest();

    private boolean mIsDataMissing;

    public GalleryCreationPresenter (GalleryCreationContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        mView = view;
        mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!mIsDataMissing) return;

        getCategories();
    }

    @Override
    public boolean isDataMissing() {
        return  mIsDataMissing;
    }

    @Override
    public void getCategories() {
        CategoryRemoteDataSource.getInstance().getList(new CategoryDataSource.GetLoadCallback() {
            @Override
            public void onLoadCategory(List<Category> categories) {
                mView.appendCategories(categories);
            }

            @Override
            public void onFailureCategory(String message) {
                mView.showErrorMessageView(message, () -> getCategories());
            }
        });
    }

    @Override
    public void setCategory(Category category) {
        mRequest.setCategoryId(category.getId());
    }

    @Override
    public void submit(String path) {
        mRequest.setPhotoUrl(path);

        GalleryRemoteDataSource.getInstance().submit(mRequest, new GalleryDataSource.PostCallback() {
            @Override
            public void onSuccess() {
                mView.showSuccessMessageView();
            }

            @Override
            public void onError(String message) {
                mView.showErrorMessageView(message, () -> submit(path));
            }
        });
    }
}
