package com.noscale.edelweiss.wp.creation;

import com.noscale.edelweiss.data.Bonus;
import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import com.noscale.edelweiss.data.source.PackageDataSource;
import com.noscale.edelweiss.data.source.remote.BuffetDataSource;
import com.noscale.edelweiss.data.source.remote.buffet.BuffetDetailsEditRequest;
import com.noscale.edelweiss.data.source.remote.buffet.BuffetRemoteDataSource;
import com.noscale.edelweiss.data.source.remote.buffet.BuffetTypeEditRequest;
import com.noscale.edelweiss.data.source.remote.wp.PackageEditRequest;
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

    private final List<Integer> mInputDetailBuffets = new ArrayList<>();

    private final PackageSubmissionRequest mRequest = new PackageSubmissionRequest();

    private final PackageEditRequest mEditRequest = new PackageEditRequest();

    private final BuffetDetailsEditRequest mBuffetDetailsEditRequest = new BuffetDetailsEditRequest();

    private final BuffetTypeEditRequest mBuffetEditRequest = new BuffetTypeEditRequest();

    private boolean mIsPackageLoad;

    private boolean mIsBuffetLoad;

    private boolean mIsPackageEdited;

    private boolean mIsBuffetTypeEdited;

    private boolean mIsBuffetDetailsEdited;

    private WeddingPackage mWeddingPackage;

    public WeddingPackageCreationPresenter (WeddingPackageCreationContract.View view, WeddingPackage weddingPackage, boolean isDataMissing) {
        view.setPresenter(this);

        this.mView = view;
        this.mIsDataMissing = isDataMissing;
        this.mWeddingPackage = weddingPackage;
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
    public void edit() {
        mBuffetEditRequest.setId(mWeddingPackage.getBuffetId());

        mEditRequest.setId(mWeddingPackage.getId());
        mEditRequest.setPackageIds(mInputDetailPackages.toArray(new Integer[mInputDetailPackages.size()]));

        mBuffetDetailsEditRequest.setId(mWeddingPackage.getBuffetId());
        mBuffetDetailsEditRequest.setDetailBuffetIds(mInputDetailBuffets.toArray(new Integer[mInputDetailBuffets.size()]));

        if (!mIsPackageEdited) {
            PackageRemoteDataSource.getInstance().edit(mEditRequest, new PackageDataSource.PostCallback() {
                @Override
                public void onSuccess() {
                    mIsPackageEdited = true;

                    if (isSuccessfulEdited()) {
                        mView.showSuccessMessage();
                    }
                }

                @Override
                public void onError(String message) {
                    mIsPackageEdited = false;
                    mView.showErrorMessage(message, () -> edit());
                }
            });
        }

        if (!mIsBuffetTypeEdited) {
            BuffetRemoteDataSource.getInstance().editBuffetType(mBuffetEditRequest, new BuffetDataSource.PostCallback() {
                @Override
                public void onSuccess() {
                    mIsBuffetTypeEdited = true;

                    if (isSuccessfulEdited()) {
                        mView.showSuccessMessage();
                    }
                }

                @Override
                public void onError(String message) {
                    mIsBuffetTypeEdited = false;
                    mView.showErrorMessage(message, () -> edit());
                }
            });
        }

        if (!mIsBuffetDetailsEdited) {
            BuffetRemoteDataSource.getInstance().editBuffetDetails(mBuffetDetailsEditRequest, new BuffetDataSource.PostCallback() {
                @Override
                public void onSuccess() {
                    mIsBuffetDetailsEdited = true;

                    if (isSuccessfulEdited()) {
                        mView.showSuccessMessage();
                    }
                }

                @Override
                public void onError(String message) {
                    mIsBuffetDetailsEdited = false;
                    mView.showErrorMessage(message, () -> edit());
                }
            });
        }
    }

    @Override
    public boolean isSuccessfulLoad() {
        return mIsBuffetLoad &&  mIsPackageLoad;
    }

    @Override
    public boolean isSuccessfulEdited() {
        return mIsPackageEdited && mIsBuffetDetailsEdited && mIsBuffetTypeEdited;
    }

    @Override
    public boolean isEdited() {
        return null != mWeddingPackage;
    }

    @Override
    public void getBuffets() {
        mIsBuffetLoad = false;

        BuffetRemoteDataSource.getInstance().getList(new BuffetDataSource.GetLoadCallback() {
            @Override
            public void onLoadBuffetTypes(List<BuffetType> types) {
                mIsBuffetLoad = true;

                BuffetType selectedType = new BuffetType();

                if (null != mWeddingPackage) {
                    selectedType.setId(mWeddingPackage.getBuffetId());
                    selectedType.setName(mWeddingPackage.getBuffetName());
                    selectedType.setDetailBuffets(mWeddingPackage.getDetailBuffets());
                }

                mView.appendBuffetType(types, selectedType);
            }

            @Override
            public void onErrorBuffetTypes(String message) {
                mIsBuffetLoad = true;
                mView.showErrorMessage(message, () -> {
                    getBuffets();
                    getPackageDetails();
                });
            }
        });
    }

    @Override
    public void getPackageDetails() {
        mIsPackageLoad = false;

        PackageRemoteDataSource.getInstance().getPackageDetails(new PackageDataSource.GetLoadPackageDetailsCallback() {
            @Override
            public void onLoadPackageDetails(List<WeddingPackageDetail> details) {
                mIsPackageLoad = true;
                mView.appendPackageDetails(
                        details,
                        (null != mWeddingPackage) ? mWeddingPackage.getDetailPackages() : null
                );
            }

            @Override
            public void onErrorPackageDetails(String message) {
                mIsPackageLoad = true;
                mView.showErrorMessage(message, () -> {
                    getBuffets();
                    getPackageDetails();
                });
            }
        });
    }

    @Override
    public void setBuffetType(BuffetType type) {
        if (isEdited()) {
            mBuffetEditRequest.setBuffetId(type.getId());
            return;
        }

        mRequest.setBuffetId(type.getId());
    }

    @Override
    public int getBuffetTypeId() {
        if (isEdited()) {
            return mBuffetEditRequest.getBuffetId();
        }

        return mRequest.getBuffetId();
    }

    @Override
    public boolean isDetailPackageNotEmpty() {
        return !mInputDetailPackages.isEmpty();
    }

    @Override
    public boolean isBuffetDetailNotEmpty() {
        return !mInputDetailBuffets.isEmpty();
    }

    @Override
    public void addDetailPackage(int id) {
        mInputDetailPackages.add(id);
    }

    @Override
    public void addDetailBuffet(int id) {
        mInputDetailBuffets.add(id);
    }

    @Override
    public void removeDetailBuffet(int id) {
        mInputDetailBuffets.remove((Object) id);
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
