package at.htl.leonding.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ToDoApi {
    @GET("todos")
    Call<ToDo[]> getAll();
}
