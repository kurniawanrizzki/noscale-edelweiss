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
        void completeBooking ();
        void appendCategory (List<Category> categories);
        void appendPackage (List<WeddingPackage> packages);
        void notifyProgressDone ();
        void showSingleErrorMessage(String message);
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void submit (int userId, String address, String phoneNumber, String eventDate, String eventTime, String bookingFee);
        void getCategories ();
        void getPackages ();
        Calendar getCalendar ();
    }
}
