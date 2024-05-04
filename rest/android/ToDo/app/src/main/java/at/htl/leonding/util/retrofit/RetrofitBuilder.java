package at.htl.leonding.util.retrofit;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Build helper for Retrofit */
@Deprecated
@Singleton
public class RetrofitBuilder {
    @Inject
    public RetrofitBuilder() {
    }
    public Retrofit build(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
