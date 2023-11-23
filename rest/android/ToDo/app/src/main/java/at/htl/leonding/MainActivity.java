package at.htl.leonding;

import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;

import at.htl.leonding.model.ToDoViewModel;
import at.htl.leonding.ui.bridge.BridgeKt;

public class MainActivity extends ComponentActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var view = new ComposeView(this);
        var viewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        BridgeKt.fillMainViewContent(view, viewModel);
        setContentView(view);
    }
}
