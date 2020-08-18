package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import com.noscale.edelweiss.data.source.remote.wp.PackageSubmissionRequest;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface PackageDataSource {

    interface GetLoadCallback {
        void onLoadWeddingPackages (List<WeddingPackage> packages);
        void onFailureWeddingPackages (String message);
    }

    interface GetLoadPackageDetailsCallback {
        void onLoadPackageDetails (List<WeddingPackageDetail> details);
        void onErrorPackageDetails (String message);
    }

    interface PostCallback {
        void onSuccess ();
        void onError (String message);
    }

    void getList (GetLoadCallback callback);

    void getPackageDetails (GetLoadPackageDetailsCallback callback);

    void submit (PackageSubmissionRequest request, PostCallback callback);
}
