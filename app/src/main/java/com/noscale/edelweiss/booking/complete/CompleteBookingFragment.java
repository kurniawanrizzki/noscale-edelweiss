package com.noscale.edelweiss.booking.complete;

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

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_booking;
    }
}
