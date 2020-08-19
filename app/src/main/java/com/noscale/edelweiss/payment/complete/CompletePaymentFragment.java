package com.noscale.edelweiss.payment.complete;

import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompletePaymentFragment extends BaseFragment implements BaseView {

    public static CompletePaymentFragment newInstance () {
        return new CompletePaymentFragment();
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_complete_payment;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }
}
