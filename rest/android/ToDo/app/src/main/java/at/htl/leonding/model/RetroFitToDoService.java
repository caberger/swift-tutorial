package at.htl.leonding.model;

import org.eclipse.microprofile.config.Config;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.util.retrofit.RetrofitBuilder;
import at.htl.leonding.util.retrofit.RetrofitClient;

/** Unused. We use ReastEasy now.
 * Service to call the <a href="https://jsonplaceholder.typicode.com/todos">ToDo REST api</a>
 * */
@Deprecated
@Singleton
public class RetroFitToDoService {
    public static final String JSON_PLACEHOLDER_BASE_URL_SETTING = "json.placeholder.baseurl";
    final private RetrofitToDoClient api;
    final private ModelStore store;

    @Inject
    public RetroFitToDoService(Config config, ModelStore store, RetrofitBuilder builder) {
        this.store = store;
        var baseUrl = config.getValue(JSON_PLACEHOLDER_BASE_URL_SETTING, String.class);
        var retrofit = builder.build(baseUrl);
        api = retrofit.create(RetrofitToDoClient.class);
    }
    public void loadAll() {
        RetrofitClient.call(api.all(), store::setTodos);
    }
}
