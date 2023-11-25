package at.htl.leonding;

import android.os.Bundle;

import javax.annotation.CheckForNull;
import javax.inject.Inject;

import androidx.activity.ComponentActivity;
import androidx.compose.ui.platform.ComposeView;
import at.htl.leonding.model.Store;
import at.htl.leonding.model.ToDoService;
import at.htl.leonding.ui.bridge.BridgeKt;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    @Inject
    Store store;
    @Inject
    ToDoService toDoService;

    @Override
    public void onCreate(@CheckForNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final var view = new ComposeView(this);
        BridgeKt.fillMainViewContent(view, store);
        setContentView(view);
        toDoService.loadAll();
    }
}
