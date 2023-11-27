package at.htl.leonding.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.Configuration;
import at.htl.leonding.util.retrofit.RetrofitBuilder;
import at.htl.leonding.util.retrofit.RetrofitClient;

/** Service to call the REST api */
@Singleton
public class ToDoService {
    final private ToDoApi api;
    final private Store store;

    @Inject
    public ToDoService(Configuration configuration, Store store, RetrofitBuilder builder) {
        this.store = store;

        var retrofit = builder.build(configuration.baseUrl());
        api = retrofit.create(ToDoApi.class);
    }
    public void loadAll() {
        RetrofitClient.call(api.getAll(), store::next);
    }
}
