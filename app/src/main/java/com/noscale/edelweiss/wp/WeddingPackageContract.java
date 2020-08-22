package com.noscale.edelweiss.wp;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.WeddingPackage;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public interface WeddingPackageContract {
    interface View extends BaseView<Presenter> {
        void goToDetail (WeddingPackage wp);
        void goToPackageCreation ();
        void delete (WeddingPackage item);
        void appendView (List<WeddingPackage> wps);
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void getPackages ();
        void delete (int id);
    }
}
