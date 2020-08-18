package com.noscale.edelweiss.data.source.remote;

import com.noscale.edelweiss.data.BuffetType;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public interface BuffetDataSource {
    interface GetLoadCallback {
        void onLoadBuffetTypes (List<BuffetType> types);
        void onErrorBuffetTypes(String message);
    }

    void getList (GetLoadCallback callback);
}
