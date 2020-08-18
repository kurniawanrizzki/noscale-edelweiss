package com.noscale.edelweiss.testimonial.creation;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public interface TestimonialCreateContract {
    interface View extends BaseView<Presenter> {
        void showSuccessMessage ();
        void showErrorMessage (String message, Runnable runnable);
    }

    interface Presenter extends BasePresenter {
        void submit (int userId, String testimonial);
    }
}
