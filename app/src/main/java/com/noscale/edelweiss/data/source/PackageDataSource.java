package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.WeddingPackage;
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

    void getList (GetLoadCallback callback);

}
