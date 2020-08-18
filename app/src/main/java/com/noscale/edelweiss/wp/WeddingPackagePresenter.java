package com.noscale.edelweiss.wp;

import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.source.PackageDataSource;
import com.noscale.edelweiss.data.source.remote.wp.PackageRemoteDataSource;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackagePresenter implements WeddingPackageContract.Presenter {

    private WeddingPackageContract.View mView;

    private boolean isDataMissing;

    public WeddingPackagePresenter (WeddingPackageContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.isDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!isDataMissing) return;

        getPackages();
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }

    @Override
    public void getPackages() {
        PackageRemoteDataSource.getInstance().getList(new PackageDataSource.GetLoadCallback() {
            @Override
            public void onLoadWeddingPackages(List<WeddingPackage> packages) {
                mView.appendView(packages);
            }

            @Override
            public void onFailureWeddingPackages(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
