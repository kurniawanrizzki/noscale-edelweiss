package com.noscale.edelweiss.schedule;

import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public enum Status {
    @SerializedName("PENDING")
    PENDING,

    @SerializedName("CANCELED")
    CANCELED,

    @SerializedName("CONFIRMED")
    CONFIRMED
}
