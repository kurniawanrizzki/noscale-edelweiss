package com.noscale.edelweiss.booking.complete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompleteBookingFragment extends BaseFragment implements BaseView {

    public static CompleteBookingFragment newInstance () {
        return new CompleteBookingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_complete_booking,
                container,
                false
        );

        return view;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    public void showProgressView(boolean isShow) {

    }
}
