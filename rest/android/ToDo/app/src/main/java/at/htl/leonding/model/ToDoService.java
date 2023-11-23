package at.htl.leonding.model;

import android.util.Log;

import io.reactivex.rxjava3.subjects.Subject;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToDoService {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String TAG = ToDoService.class.getName();
    final private ToDoClient api;
    final private Subject<ToDoModel> store;

    public ToDoService(Subject<ToDoModel> store) {
        this.store = store;
        var retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ToDoClient.class);
    }
    public ToDo[] all() {

        ToDo[] todos = new ToDo[0];
        /*
        try (var client = createClient()) {
            var path = UriBuilder.fromPath("https://jsonplaceholder.typicode.com");
            var target = client.target(path);
            var proxy = target.proxy(ToDoService.class);
            todos = proxy.all();
        } catch(Exception e) {
            Log.e(TAG, "failed to load", e);
        }

         */
        return todos;
    }
}
