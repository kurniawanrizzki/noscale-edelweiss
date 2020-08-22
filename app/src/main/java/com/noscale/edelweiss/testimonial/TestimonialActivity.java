package com.noscale.edelweiss.testimonial;

import android.os.Bundle;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class TestimonialActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        mFragment = TestimonialFragment.newInstance();

        mPresenter = new TestimonialPresenter(
                (TestimonialContract.View) mFragment,
                shouldLoadDataFromRepository(savedInstanceState)
        );
    }

    @Override
    protected int getActivityTitle() {
        return R.string.testimonial_txt;
    }
}
