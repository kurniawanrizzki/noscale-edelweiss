package com.noscale.edelweiss.testimonial;

import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Testimonial;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public interface TestimonialContract {
    interface View extends BaseView<Presenter> {
        void showPage (List<Testimonial> testimonials);
        void showEmptyPage ();
        void showErrorMessage (String message);
    }

    interface Presenter extends BasePresenter {
        void fetch ();
    }
}
