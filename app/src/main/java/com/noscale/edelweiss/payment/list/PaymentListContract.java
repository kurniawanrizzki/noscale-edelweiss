package com.noscale.edelweiss.payment.list;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.PaymentHistory;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public interface PaymentListContract {
    interface View extends BaseView<Presenter> {
        void append (List<PaymentHistory> histories);
        void showErrorMessage (String message, Runnable runnable);
    }

    interface Presenter extends BasePresenter {
        void fetch ();
    }
}
