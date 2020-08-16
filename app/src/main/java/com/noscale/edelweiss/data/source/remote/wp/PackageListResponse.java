package com.noscale.edelweiss.data.source.remote.wp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class PackageListResponse extends BaseResponse {

    @SerializedName("weddingPackages")
    @Expose
    private List<WeddingPackage> packages;

    public List<WeddingPackage> getPackages() {
        return packages;
    }
}
