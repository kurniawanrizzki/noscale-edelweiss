package com.noscale.edelweiss.booking;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.WeddingPackage;

import java.util.Calendar;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void goToCompletionBooking();
        void appendCategory (List<Category> categories);
        void appendPackage (List<WeddingPackage> packages);
        void showErrorMessage (String message, Runnable runnable);
    }

    interface Presenter extends BasePresenter {
        void submit (int userId, String address, String phoneNumber, String eventDate, String eventTime, Float bookingFee);
        void setSelectedCategory (Category category);
        void setSelectedWeddingPackage (WeddingPackage wp);
        void getCategories ();
        void getPackages ();
        void setTimeInput (int hour, int minutes);
        void setDateInput (int year, int month, int day);
        String getTimeInput ();
        String getDateInput ();
        boolean isDataSuccessfulLoad ();
    }
}
