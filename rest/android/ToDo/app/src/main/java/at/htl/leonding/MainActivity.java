package at.htl.leonding;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.activity.ComponentActivity;
import at.htl.leonding.model.ToDoService;
import at.htl.leonding.ui.layout.MainView;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    @Inject
    ToDoService toDoService;
    @Inject
    MainView mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView.setContentOfActivity(this);
        toDoService.loadAll();
    }
}
