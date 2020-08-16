package com.noscale.edelweiss.payment;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.source.PaymentType;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface PaymentContract {
    interface View extends BaseView<Presenter> {
        void appendData (List<PaymentType> types);
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void fetch ();
    }
}
