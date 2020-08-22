package com.noscale.edelweiss.gallery.creation;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Category;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public interface GalleryCreationContract {
    interface View extends BaseView<Presenter> {
        void openGallery ();
        void appendCategories (List<Category> categories);
        void showSuccessMessageView ();
        void showErrorMessageView (String message, Runnable runnable);
    }

    interface Presenter extends BasePresenter {
        void getCategories ();
        void setCategory (Category category);
        void submit (String path);
    }
}
