package at.htl.leonding.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/** The Java Representation of the REST api for {@link ToDo}s in our <a href="https://jsonplaceholder.typicode.com/">json-placeholder</a>  api */
@Deprecated
public interface RetrofitToDoClient {
    @GET("todos")
    Call<ToDo[]> all();
}
