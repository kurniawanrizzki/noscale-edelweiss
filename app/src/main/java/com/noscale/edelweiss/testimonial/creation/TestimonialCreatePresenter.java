package com.noscale.edelweiss.testimonial.creation;


import com.noscale.edelweiss.data.source.TestimonialDataSource;
import com.noscale.edelweiss.data.source.remote.testimonial.TestimonialRemoteDataSource;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class TestimonialCreatePresenter implements TestimonialCreateContract.Presenter {

    private TestimonialCreateContract.View mView;

    public TestimonialCreatePresenter (TestimonialCreateContract.View view) {
        view.setPresenter(this);
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    @Override
    public void submit(int userId, String testimonial) {
        TestimonialRemoteDataSource.getInstance().submit(
                userId, testimonial,
                new TestimonialDataSource.PostCallback() {
                    @Override
                    public void onSuccess() {
                        mView.showSuccessMessage();
                    }

                    @Override
                    public void onError(String message) {
                        mView.showErrorMessage(message, () -> submit(userId, testimonial));
                    }
                }
        );
    }
}
