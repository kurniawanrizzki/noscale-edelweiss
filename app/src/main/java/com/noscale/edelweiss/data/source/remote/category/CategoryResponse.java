package com.noscale.edelweiss.data.source.remote.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.source.remote.BaseResponse;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class CategoryResponse extends BaseResponse {

    @SerializedName("weddingCategories")
    @Expose
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
