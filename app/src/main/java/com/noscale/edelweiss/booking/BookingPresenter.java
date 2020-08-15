package com.noscale.edelweiss.booking;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingPresenter implements BookingContract.Presenter {

    private BookingContract.View mView;

    private boolean mIsDataMissing;

    public BookingPresenter (BookingContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!mIsDataMissing) return;
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
