package com.noscale.edelweiss.data.source.remote.testimonial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class TestimonialSubmissionRequest {

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("testimoni")
    @Expose
    private String testimonial;

    public TestimonialSubmissionRequest (String testimonial, int userId) {
        this.testimonial = testimonial;
        this.userId = userId;
    }
}
