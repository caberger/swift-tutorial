package at.htl.leonding.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.Configuration;
import at.htl.leonding.util.RetrofitClient;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ToDoService {
    final private ToDoApi api;
    final private Store store;
    final Configuration configuration;

    @Inject
    public ToDoService(Configuration configuration, Store store) {
        this.store = store;
        this.configuration = configuration;
        var retrofit = new Builder()
                .baseUrl(configuration.baseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ToDoApi.class);
    }
    public void loadAll() {
        RetrofitClient.call(api.getAll(), store::next);
    }
}
