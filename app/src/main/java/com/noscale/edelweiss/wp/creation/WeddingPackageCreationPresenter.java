package com.noscale.edelweiss.wp.creation;

import com.noscale.edelweiss.data.Bonus;
import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import com.noscale.edelweiss.data.source.PackageDataSource;
import com.noscale.edelweiss.data.source.remote.BuffetDataSource;
import com.noscale.edelweiss.data.source.remote.buffet.BuffetRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.wp.PackageRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.wp.PackageSubmissionRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class WeddingPackageCreationPresenter implements WeddingPackageCreationContract.Presenter {

    private WeddingPackageCreationContract.View mView;

    private boolean mIsDataMissing;

    private final List<Integer> mInputDetailPackages = new ArrayList<>();

    private final PackageSubmissionRequest mRequest = new PackageSubmissionRequest();

    public WeddingPackageCreationPresenter (WeddingPackageCreationContract.View view, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (!mIsDataMissing) return;

        getBuffets();
        getPackageDetails();
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    @Override
    public void submit(String packageName, String packagePrice, String totalBuffet, List<Bonus> bonuses) {
        mRequest.setName(packageName);
        mRequest.setPrice(packagePrice);
        mRequest.setTotalBuffet(totalBuffet);
        mRequest.setPackageIds(mInputDetailPackages.toArray(new Integer[mInputDetailPackages.size()]));
        mRequest.setBonus(toStringArray(bonuses));

        PackageRemoteDataSource.getInstance().submit(mRequest, new PackageDataSource.PostCallback() {
            @Override
            public void onSuccess() {
                mView.showSuccessMessage();
            }

            @Override
            public void onError(String message) {
                mView.showErrorMessage(message, () -> submit(packageName, packagePrice, totalBuffet, bonuses));
            }
        });
    }

    @Override
    public void getBuffets() {
        BuffetRemoteDataSource.getInstance().getList(new BuffetDataSource.GetLoadCallback() {
            @Override
            public void onLoadBuffetTypes(List<BuffetType> types) {
                mView.appendBuffetType(types);
            }

            @Override
            public void onErrorBuffetTypes(String message) {
                mView.showErrorMessage(message, () -> getBuffets());
            }
        });
    }

    @Override
    public void getPackageDetails() {
        PackageRemoteDataSource.getInstance().getPackageDetails(new PackageDataSource.GetLoadPackageDetailsCallback() {
            @Override
            public void onLoadPackageDetails(List<WeddingPackageDetail> details) {
                mView.appendPackageDetails(details);
            }

            @Override
            public void onErrorPackageDetails(String message) {
                mView.showErrorMessage(message, () -> getPackageDetails());
            }
        });
    }

    @Override
    public void setBuffetType(BuffetType type) {
        mRequest.setBuffetId(type.getId());
    }

    @Override
    public boolean isDetailPackageNotEmpty() {
        return !mInputDetailPackages.isEmpty();
    }

    @Override
    public void addDetailPackage(int id) {
        mInputDetailPackages.add(id);
    }

    @Override
    public void removeDetailPackage(int id) {
        mInputDetailPackages.remove((Object) id);
    }

    @Override
    public String[] toStringArray(List<Bonus> bonuses) {
        String[] items = new String[bonuses.size()];

        for (int i = 0; i < bonuses.size(); i ++) {
            Bonus b = bonuses.get(i);

            if (null == b) continue;
            items[i] = b.getContent();
        }

        return items;
    }
}
