package com.noscale.edelweiss.testimonial.creation;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

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

    @Override
    protected int getResContentView() {
        return R.layout.activity_base_no_action_bar;
    }

    @Override
    protected int getActivityTitle() {
        return 0;
    }
}
