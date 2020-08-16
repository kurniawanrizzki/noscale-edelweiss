package com.noscale.edelweiss.data.source.remote.booking;

import com.noscale.edelweiss.data.source.BookingDataSource;
import com.noscale.edelweiss.data.source.remote.APIService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class BookingRemoteDataSource implements BookingDataSource {

    private APIService mService;

    private static BookingRemoteDataSource instance;

    public static BookingRemoteDataSource getInstance() {
        if (null == instance) instance = new BookingRemoteDataSource();
        return instance;
    }

    public BookingRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void submit(BookingSubmissionRequest request, PostCallback callback) {
        Call<BookingSubmissionResponse> response = mService.getBookingApi().submit(request);
        response.enqueue(new Callback<BookingSubmissionResponse>() {
            @Override
            public void onResponse(Call<BookingSubmissionResponse> call, Response<BookingSubmissionResponse> response) {
                try {
                    BookingSubmissionResponse res = response.body();

                    if ((null != res) && res.isOk()) {
                        callback.onSuccess();
                    }

                    callback.onFailure(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BookingSubmissionResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onFailure(message);
            }
        });
    }
}
