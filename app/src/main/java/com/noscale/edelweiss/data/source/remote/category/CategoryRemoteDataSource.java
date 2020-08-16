package com.noscale.edelweiss.data.source.remote.category;

import com.noscale.edelweiss.data.Category;
import com.noscale.edelweiss.data.source.CategoryDataSource;
import com.noscale.edelweiss.data.source.remote.APIService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 17/08/20.
 */
public class CategoryRemoteDataSource implements CategoryDataSource {

    private APIService mService;

    private static CategoryRemoteDataSource instance;

    public static CategoryRemoteDataSource getInstance() {
        if (null == instance) instance = new CategoryRemoteDataSource();
        return instance;
    }

    public CategoryRemoteDataSource() {
        mService = APIService.getInstance();
    }

    @Override
    public void getList(GetLoadCallback callback) {
        Call<CategoryResponse> response = mService.getCategoryApi().getCategories();
        response.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                CategoryResponse res = response.body();

                if ((null != res) && res.isOk()) {
                    List<Category> categories = res.getCategories();
                    callback.onLoadCategory(categories);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                callback.onFailureCategory(message);
            }
        });
    }
}
