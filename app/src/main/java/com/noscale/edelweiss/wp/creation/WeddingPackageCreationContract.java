package com.noscale.edelweiss.wp.creation;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Bonus;
import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.WeddingPackageDetail;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public interface WeddingPackageCreationContract {
    interface View extends BaseView<Presenter> {
        void appendBuffetType (List<BuffetType> buffetTypes, BuffetType selectedType);
        void appendPackageDetails (List<WeddingPackageDetail> details, List<WeddingPackageDetail> orderDetails);
        void showSuccessMessage ();
        void showErrorMessage (String message, Runnable runnable);
        void hideWhenEdit ();
    }

    interface Presenter extends BasePresenter {
        void getBuffets ();
        void getPackageDetails ();
        void setBuffetType (BuffetType type);
        int getBuffetTypeId ();
        boolean isDetailPackageNotEmpty ();
        boolean isBuffetDetailNotEmpty ();
        void addDetailPackage (int id);
        void addDetailBuffet (int id);
        void removeDetailBuffet (int id);
        void removeDetailPackage (int id);
        String[] toStringArray (List<Bonus> bonuses);
        void submit (String packageName, String packagePrice, String totalBuffet, List<Bonus> bonuses);
        void edit ();
        boolean isSuccessfulLoad ();
        boolean isEdited ();
    }
}
