package com.noscale.edelweiss.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.booking.complete.CompleteBookingActivity;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.WeddingPackage;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingFragment extends BaseFragment implements BookingContract.View {

    public static BookingFragment newInstance () {
        return new BookingFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.b_booking_submit).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getContext(), CompleteBookingActivity.class);
                        startActivity(i);
                    }
                }
        );

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_booking;
    }

    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void appendCategory(List<Category> categories) {

    }

    @Override
    public void appendPackage(List<WeddingPackage> packages) {

    }

    @Override
    public void notifyProgressDone() {
        showProgressView(false);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message);
    }
}
