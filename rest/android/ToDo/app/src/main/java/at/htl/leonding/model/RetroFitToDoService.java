package at.htl.leonding.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.Configuration;
import at.htl.leonding.util.retrofit.RetrofitBuilder;
import at.htl.leonding.util.retrofit.RetrofitClient;

/** Unused. We use ReastEasy now.
 * Service to call the <a href="https://jsonplaceholder.typicode.com/todos">ToDo REST api</a>
 * */
@Deprecated
@Singleton
public class RetroFitToDoService {
    final private RetrofitToDoClient api;
    final private Store store;

    @Inject
    public RetroFitToDoService(Configuration config, Store store, RetrofitBuilder builder) {
        this.store = store;
        var baseUrl = config.getBaseUrl();
        var retrofit = builder.build(baseUrl);
        api = retrofit.create(RetrofitToDoClient.class);
    }
    public void loadAll() {
        RetrofitClient.call(api.all(), store::set);
    }
}
