package com.noscale.edelweiss.data.source.remote;

import com.noscale.edelweiss.data.source.remote.authentication.AuthenticationAPI;
import com.noscale.edelweiss.data.source.remote.category.CategoryAPI;
import com.noscale.edelweiss.data.source.remote.payment.PaymentAPI;
import com.noscale.edelweiss.data.source.remote.schedule.ScheduleAPI;
import com.noscale.edelweiss.data.source.remote.testimonial.TestimonialAPI;
import com.noscale.edelweiss.data.source.remote.wp.PackageAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class APIService {

    private static APIService instance;

    private static final String BASE_URL = "http://192.168.43.254:6666/edelwish-service/v1/";

    private static final String BASE_URL_ALT = "http://192.168.43.254:6666/edelwish-service/v2/";

    private AuthenticationAPI mAuthenticationApi;

    private ScheduleAPI mScheduleApi;

    private TestimonialAPI mTestimonialApi;

    private PaymentAPI mPaymentApi;

    private CategoryAPI mCategoryApi;

    private PackageAPI mPackageApi;

    public static APIService getInstance() {
        if (null == instance) instance = new APIService();
        return instance;
    }

    public APIService() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit altClient = new Retrofit.Builder()
                .baseUrl(BASE_URL_ALT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAuthenticationApi = client.create(AuthenticationAPI.class);
        mScheduleApi = client.create(ScheduleAPI.class);
        mTestimonialApi = client.create(TestimonialAPI.class);
        mPaymentApi = client.create(PaymentAPI.class);
        mCategoryApi = client.create(CategoryAPI.class);
        mPackageApi = altClient.create(PackageAPI.class);
    }

    public AuthenticationAPI getAuthenticationApi () {
        return mAuthenticationApi;
    }

    public ScheduleAPI getScheduleApi () {
        return mScheduleApi;
    }

    public TestimonialAPI getTestimonialApi () {
        return mTestimonialApi;
    }

    public PaymentAPI getPaymentApi () {
        return mPaymentApi;
    }

    public CategoryAPI getCategoryApi() {
        return mCategoryApi;
    }

    public PackageAPI getPackageApi () {
        return mPackageApi;
    }
}
