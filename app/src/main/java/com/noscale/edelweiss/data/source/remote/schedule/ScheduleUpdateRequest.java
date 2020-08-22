package com.noscale.edelweiss.data.source.remote.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class ScheduleUpdateRequest {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("bookingStatus")
    @Expose
    private String status;

    public ScheduleUpdateRequest (int id, String status) {
        this.id = id;
        this.status = status;
    }

}
