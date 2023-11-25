package at.htl.leonding.util;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClient {
    private static final String TAG = RetrofitClient.class.getName();

    public interface RetrofitAdapterCallback<T> {
        void done(T t);
    }
    public interface RetrofitErrorCallback<T> {
        void report(Call<T> call, Throwable t);
    }
    public static <T> void call(Call<T> call, RetrofitAdapterCallback<T> callback, RetrofitErrorCallback<T> error) {
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
    public static <T> void call(Call<T> call, RetrofitAdapterCallback<T> callback) {
        call(call, callback, (cll, t) -> Log.e(TAG, "failed to execute request", t));
    }
}
