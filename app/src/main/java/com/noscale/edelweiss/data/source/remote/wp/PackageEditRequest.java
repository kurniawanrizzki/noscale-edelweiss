package com.noscale.edelweiss.data.source.remote.wp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PackageEditRequest {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("detailPackageIds")
    @Expose
    private Integer[] packageIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer[] getPackageIds() {
        return packageIds;
    }

    public void setPackageIds(Integer[] packageIds) {
        this.packageIds = packageIds;
    }
}
