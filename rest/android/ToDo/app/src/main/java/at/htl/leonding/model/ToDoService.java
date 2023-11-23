package at.htl.leonding.model;

import java.util.List;

import at.htl.leonding.util.RetrofitAdapter;
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
    public void loadAll() {
        new RetrofitAdapter<ToDo[]>().enqueue(api.getAll(), todos -> store.onNext(new ToDoModel(List.of(todos))));
    }
}
