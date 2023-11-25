package at.htl.leonding.model;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ToDoClient {
    @GET("todos")
    Call<ToDo[]> getAll();
}
