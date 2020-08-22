package com.noscale.edelweiss.gallery;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.Gallery;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public interface GalleryContract {
    interface View extends BaseView<Presenter> {
        void goToGalleryCreation ();
        void addToFilter (List<Category> categories);
        void append (List<Gallery> galleries);
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void fetch ();
        void filter (String title);
    }
}
