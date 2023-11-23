package at.htl.leonding.util;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitAdapter<T> {
    private static final String TAG = RetrofitAdapter.class.getName();

    public interface RetrofitAdapterCallback<T> {
        void done(T t);
    }
    public interface RetrofitErrorCallback<T> {
        void report(Call<T> call, Throwable t);
    }
    public void enqueue(Call<T> call, RetrofitAdapterCallback<T> callback, RetrofitErrorCallback error) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                callback.done(response.body());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                error.report(call, t);
            }
        });
    }
    public void enqueue(Call<T> call, RetrofitAdapterCallback<T> callback) {
        enqueue(call, callback, (cll, t) -> Log.e(TAG, "failed to execute request", t));
    }
}
