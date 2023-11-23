package at.htl.leonding;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;

import org.jboss.jandex.Main;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import at.htl.leonding.model.ToDo;
import at.htl.leonding.model.ToDoModel;
import at.htl.leonding.model.ToDoService;
import at.htl.leonding.model.ToDoViewModel;
import at.htl.leonding.ui.bridge.BridgeKt;

public class MainActivity extends ComponentActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var view = new ComposeView(this);
        var viewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        BridgeKt.fillMainViewContent(view, viewModel);
        setContentView(view);
        var service = new ToDoService();
        CompletableFuture
                .supplyAsync(service::all)
                //.thenAccept(todos -> viewModel.getStore().onNext(new ToDoModel(List.of(todos))));
                .thenAccept(this::updateTodos);
    }
    void updateTodos(ToDo[] toDos) {
        Log.d(TAG, "todos loaded " + toDos.length);
    }
}
