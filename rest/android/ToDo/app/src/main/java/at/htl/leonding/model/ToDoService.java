package at.htl.leonding.model;

import org.eclipse.microprofile.config.Config;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.Configuration;
import at.htl.leonding.util.retrofit.RetrofitBuilder;
import at.htl.leonding.util.retrofit.RetrofitClient;

/** Service to call the <a href="https://jsonplaceholder.typicode.com/todos">ToDo REST api</a>  */
@Singleton
public class ToDoService {
    final private ToDoApi api;
    final private Store store;

    @Inject
    public ToDoService(Configuration config, Store store, RetrofitBuilder builder) {
        this.store = store;
        var baseUrl = config.getBaseUrl();
        var retrofit = builder.build(baseUrl);
        api = retrofit.create(ToDoApi.class);
    }
    public void loadAll() {
        RetrofitClient.call(api.getAll(), store::next);
    }
}
