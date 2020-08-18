package com.noscale.edelweiss.testimonial.creation;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class TestimonialCreateActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = TestimonialCreateFragment.newInstance();
        mPresenter = new TestimonialCreatePresenter(
                (TestimonialCreateContract.View) mFragment
        );
    }
}
