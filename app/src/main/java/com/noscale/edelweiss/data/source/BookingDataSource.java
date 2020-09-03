package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.Building;
import com.noscale.edelweiss.data.source.remote.booking.BookingSubmissionRequest;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface BookingDataSource {
    interface PostCallback {
        void onSuccess ();
        void onFailure (String message);
    }

    interface GetCallback {
        void onSuccess (List<Building> data);
        void onFailure (String message);
    }

    void submit (BookingSubmissionRequest request, PostCallback callback);

    void getBuilding (int packageId, GetCallback callback);
}
