package com.noscale.edelweiss.booking;

import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.source.CategoryDataSource;
import com.noscale.edelweiss.data.source.PackageDataSource;
import com.noscale.edelweiss.data.source.remote.category.CategoryRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.wp.PackageRemoteDataSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class BookingPresenter implements BookingContract.Presenter {

    private BookingContract.View mView;

    private final static LinkedHashMap<String, Boolean> APIMap = new LinkedHashMap<>(2);

    private boolean mIsDataMissing;

    static {
        APIMap.put("category", false);
        APIMap.put("package", false);
    }

    public BookingPresenter (BookingContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!mIsDataMissing) return;

        getCategories();
        getPackages();
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    @Override
    public void getCategories() {
        boolean isCategoryDone = APIMap.get("category");

        if (isCategoryDone) return;

        CategoryRemoteDataSource.getInstance().getList(new CategoryDataSource.GetLoadCallback() {
            @Override
            public void onLoadCategory(List<Category> categories) {
                boolean isDone = false;
                APIMap.put("category", true);

                for (Map.Entry<String, Boolean> entry : APIMap.entrySet()) {
                    isDone = entry.getValue();
                }

                if (isDone) {
                    mView.notifyProgressDone();
                }

                mView.appendCategory(categories);
            }

            @Override
            public void onFailureCategory(String message) {
                mView.showErrorMessage(message);
            }
        });
    }

    @Override
    public void getPackages() {
        boolean isPackageDone = APIMap.get("package");

        if (isPackageDone) return;

        PackageRemoteDataSource.getInstance().getList(new PackageDataSource.GetLoadCallback() {
            @Override
            public void onLoadWeddingPackages(List<WeddingPackage> packages) {
                boolean isDone = false;
                APIMap.put("package", true);

                for (Map.Entry<String, Boolean> entry : APIMap.entrySet()) {
                    isDone = entry.getValue();
                }

                if (isDone) {
                    mView.notifyProgressDone();
                }

                mView.appendPackage(packages);
            }

            @Override
            public void onFailureWeddingPackages(String message) {
                mView.showErrorMessage(message);
            }
        });
    }
}
