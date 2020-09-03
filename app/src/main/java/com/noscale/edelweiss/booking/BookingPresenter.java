package com.noscale.edelweiss.booking;

import com.noscale.edelweiss.data.Building;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.source.BookingDataSource;
import com.noscale.edelweiss.data.source.CategoryDataSource;
import com.noscale.edelweiss.data.source.PackageDataSource;
import com.noscale.edelweiss.data.source.remote.booking.BookingRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.booking.BookingSubmissionRequest;
import com.noscale.edelweiss.data.source.remote.category.CategoryRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.wp.PackageRemoteDataSource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingPresenter implements BookingContract.Presenter {

    private BookingContract.View mView;

    private boolean mIsDataMissing;

    private boolean mIsPackageLoad;

    private boolean mIsCategoryLoad;

    private final Calendar mCalendar = Calendar.getInstance(Locale.getDefault());

    private static final String DATE_FORMAT = "YYYY-MM-dd";

    private static final String TIME_FORMAT = "HH:mm";

    private final BookingSubmissionRequest mRequest = new BookingSubmissionRequest();

    public BookingPresenter (BookingContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!mIsDataMissing) return;

        getCategories();
        getPackages();
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    @Override
    public void submit(int userId, String address, String phoneNumber, String eventDate, String eventTime) {

        mRequest.setUserId(userId);
        mRequest.setAddress(address);
        mRequest.setPhoneNumber(phoneNumber);
        mRequest.setEventDateTime(eventDate+" "+eventTime);

        BookingRemoteDataSource.getInstance().submit(mRequest, new BookingDataSource.PostCallback() {
            @Override
            public void onSuccess() {
                mView.goToCompletionBooking();
            }

            @Override
            public void onFailure(String message) {
                mView.showErrorMessage(message, () -> submit(
                        userId,
                        address,
                        phoneNumber,
                        eventDate,
                        eventTime
                ));
            }
        });
    }

    @Override
    public void setSelectedCategory(Category category) {
        mRequest.setWeddingCategory(category.getId());
    }

    @Override
    public void setSelectedWeddingPackage(WeddingPackage wp) {
        mRequest.setWeddingPackage(wp.getId());
        getBuilding(wp.getId());
    }

    @Override
    public void setBuilding(Building b) {
        if (null == b) mRequest.setWeddingBuilding(0);
        mRequest.setWeddingBuilding(b.getId());
    }

    @Override
    public void getCategories() {
        mIsCategoryLoad = false;

        CategoryRemoteDataSource.getInstance().getList(new CategoryDataSource.GetLoadCallback() {
            @Override
            public void onLoadCategory(List<Category> categories) {
                mIsCategoryLoad = true;
                mView.appendCategory(categories);
            }

            @Override
            public void onFailureCategory(String message) {
                mIsCategoryLoad = true;
                mView.showErrorMessage(message, () -> {
                    getCategories();
                    getPackages();
                });
            }
        });
    }

    @Override
    public void getPackages() {
        mIsPackageLoad = false;

        PackageRemoteDataSource.getInstance().getList(new PackageDataSource.GetLoadCallback() {
            @Override
            public void onLoadWeddingPackages(List<WeddingPackage> packages) {
                mIsPackageLoad = true;
                mView.appendPackage(packages);
            }

            @Override
            public void onFailureWeddingPackages(String message) {
                mIsPackageLoad = true;
                mView.showErrorMessage(message, () -> {
                    getCategories();
                    getPackages();
                });
            }
        });
    }

    @Override
    public void getBuilding(int packageId) {
        BookingRemoteDataSource.getInstance().getBuilding(packageId, new BookingDataSource.GetCallback() {
            @Override
            public void onSuccess(List<Building> data) {
                mView.appendBuilding(data);
            }

            @Override
            public void onFailure(String message) {
                mView.showErrorMessage(message, () -> getBuilding(packageId));
            }
        });
    }

    @Override
    public void setTimeInput(int hour, int minutes) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        mCalendar.set(Calendar.MINUTE, minutes);
    }

    @Override
    public String getTimeInput () {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return sdf.format(mCalendar.getTime());
    }

    @Override
    public void setDateInput(int year, int month, int day) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);
    }

    @Override
    public String getDateInput() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return sdf.format(mCalendar.getTime());
    }

    @Override
    public boolean isDataSuccessfulLoad() {
        return mIsCategoryLoad && mIsPackageLoad;
    }
}
