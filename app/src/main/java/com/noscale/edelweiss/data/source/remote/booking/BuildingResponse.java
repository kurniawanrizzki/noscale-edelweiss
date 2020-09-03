package com.noscale.edelweiss.data.source.remote.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.Building;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 04/09/20.
 */
public class BuildingResponse extends BaseResponse {

    @SerializedName("buildingList")
    @Expose
    private List<Building> data;

    public List<Building> getData() {
        return data;
    }
}
