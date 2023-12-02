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
    public static final String JSON_PLACEHOLDER_BASE_URL_SETTING = "json.placeholder.baseurl";
    final private ToDoApi api;
    final private Store store;

    @Inject
    public ToDoService(Config config, Store store, RetrofitBuilder builder) {
        this.store = store;

        var retrofit = builder.build(config.getValue(JSON_PLACEHOLDER_BASE_URL_SETTING, String.class));
        api = retrofit.create(ToDoApi.class);
    }
    public void loadAll() {
        RetrofitClient.call(api.getAll(), store::next);
    }
}
