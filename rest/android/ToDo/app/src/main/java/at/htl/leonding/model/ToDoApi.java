package at.htl.leonding.model;

import retrofit2.Call;
import retrofit2.http.GET;

/** The Java Representation of a {@link ToDo} in our <a href="https://jsonplaceholder.typicode.com/">json-placeholder api</a> */
public interface ToDoApi {
    @GET("todos")
    Call<ToDo[]> getAll();
}
