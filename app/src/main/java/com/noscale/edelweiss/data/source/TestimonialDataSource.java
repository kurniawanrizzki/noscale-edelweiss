package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.Testimonial;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public interface TestimonialDataSource {

    interface GetLoadCallback {
        void onLoadTestimonial (List<Testimonial> testimonials);
        void onEmptyTestimonial ();
        void onLoadTestimonialFailure (String message);
    }

    void getList (GetLoadCallback callback);
}
