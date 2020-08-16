package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.Category;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface CategoryDataSource {

    interface GetLoadCallback {
        void onLoadCategory (List<Category> categories);
        void onFailureCategory (String message);
    }

    void getList (GetLoadCallback callback);

}
