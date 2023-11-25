package at.htl.leonding.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.Configuration;
import at.htl.leonding.util.RetrofitAdapter;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ToDoService {
    final private ToDoClient api;
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
        api = retrofit.create(ToDoClient.class);
    }
    public void loadAll() {
        var adapter = new RetrofitAdapter<ToDo[]>();
        adapter.enqueue(api.getAll(), todos -> store.toDoModel().onNext(new ToDoModel(List.of(todos))));
    }
}
