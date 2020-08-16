package com.noscale.edelweiss.data.source;

import com.noscale.edelweiss.data.source.remote.booking.BookingSubmissionRequest;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public interface BookingDataSource {
    interface PostCallback {
        void onSuccess ();
        void onFailure (String message);
    }

    void submit (BookingSubmissionRequest request, PostCallback callback);
}
