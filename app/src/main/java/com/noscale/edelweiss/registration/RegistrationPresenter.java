package com.noscale.edelweiss.registration;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View mView;

    private boolean isDataMissing;

    public RegistrationPresenter (RegistrationContract.View view, boolean isDataMissing) {
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
