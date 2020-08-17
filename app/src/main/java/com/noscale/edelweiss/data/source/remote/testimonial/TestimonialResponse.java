package com.noscale.edelweiss.data.source.remote.testimonial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.Testimonial;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class TestimonialResponse extends BaseResponse {

    @SerializedName("testimoniList")
    @Expose
    private List<Testimonial> testimonials;

    public List<Testimonial> getTestimonials () {
        return testimonials;
    }
}
