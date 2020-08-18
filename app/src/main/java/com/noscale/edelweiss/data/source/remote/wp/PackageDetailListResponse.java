package com.noscale.edelweiss.data.source.remote.wp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class PackageDetailListResponse extends BaseResponse {

    @SerializedName("detailPackages")
    @Expose
    private List<WeddingPackageDetail> details;

    public List<WeddingPackageDetail> getDetails () {
        return details;
    }
}
