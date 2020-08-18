package com.noscale.edelweiss.wp.creation;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Bonus;
import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.WeddingPackageDetail;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public interface WeddingPackageCreationContract {
    interface View extends BaseView<Presenter> {
        void appendBuffetType (List<BuffetType> buffetTypes);
        void appendPackageDetails (List<WeddingPackageDetail> details);
        void showSuccessMessage ();
        void showErrorMessage (String message, Runnable runnable);
    }

    interface Presenter extends BasePresenter {
        void getBuffets ();
        void getPackageDetails ();
        void setBuffetType (BuffetType type);
        boolean isDetailPackageNotEmpty ();
        void addDetailPackage (int id);
        void removeDetailPackage (int id);
        String[] toStringArray (List<Bonus> bonuses);
        void submit (String packageName, String packagePrice, String totalBuffet, List<Bonus> bonuses);
    }
}
