package at.htl.leonding.model;

import retrofit2.Call;
import retrofit2.http.GET;

/** The Java Representation of the REST api for {@link ToDo}s in our <a href="https://jsonplaceholder.typicode.com/">json-placeholder</a>  api*/
public interface ToDoApi {
    @GET("todos")
    Call<ToDo[]> getAll();
}
