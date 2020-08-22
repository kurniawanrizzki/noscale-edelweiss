package com.noscale.edelweiss.data.source.remote;

import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.source.remote.buffet.BuffetDetailsEditRequest;
import com.noscale.edelweiss.data.source.remote.buffet.BuffetTypeEditRequest;
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

    interface PostCallback {
        void onSuccess ();
        void onError (String message);
    }

    void getList (GetLoadCallback callback);

    void editBuffetType (BuffetTypeEditRequest request, PostCallback callback);

    void editBuffetDetails (BuffetDetailsEditRequest request, PostCallback callback);
}
