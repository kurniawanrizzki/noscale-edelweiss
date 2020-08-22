package com.noscale.edelweiss.about;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public interface AboutUsContract {
    interface View extends BaseView<Presenter> {
        void append (String content);
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void loadContent ();
    }
}
