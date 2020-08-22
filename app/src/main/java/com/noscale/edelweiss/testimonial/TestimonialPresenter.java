package com.noscale.edelweiss.testimonial;

import com.noscale.edelweiss.data.Testimonial;
import com.noscale.edelweiss.data.source.TestimonialDataSource;
import com.noscale.edelweiss.data.source.remote.testimonial.TestimonialRemoteDataSource;
import java.util.List;

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

        fetch();
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }

    @Override
    public void fetch() {
        TestimonialRemoteDataSource.getInstance().getList(new TestimonialDataSource.GetLoadCallback() {
            @Override
            public void onLoadTestimonial(List<Testimonial> testimonials) {
                mView.showPage(testimonials);
            }

            @Override
            public void onLoadTestimonialFailure(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
