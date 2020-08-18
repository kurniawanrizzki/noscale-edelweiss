package com.noscale.edelweiss.data.source.remote.buffet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.BuffetType;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class BuffetTypeResponse extends BaseResponse {

    @SerializedName("buffets")
    @Expose
    private List<BuffetType> types;

    public List<BuffetType> getTypes() {
        return types;
    }
}
