package com.noscale.edelweiss.booking;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.WeddingPackage;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void appendCategory (List<Category> categories);
        void appendPackage (List<WeddingPackage> packages);
        void notifyProgressDone ();
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void getCategories ();
        void getPackages ();
    }
}
