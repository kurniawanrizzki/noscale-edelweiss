package com.noscale.edelweiss.testimonial;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class TestimonialPresenter implements TestimonialContract.Presenter {

    private TestimonialContract.View mView;

    private boolean isDataMissing;

    public TestimonialPresenter (TestimonialContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.isDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!isDataMissing) return;
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }
}
