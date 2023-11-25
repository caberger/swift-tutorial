package at.htl.leonding;

import android.os.Bundle;

import javax.annotation.CheckForNull;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

import androidx.activity.ComponentActivity;
import at.htl.leonding.model.ToDoService;
import at.htl.leonding.ui.layout.MainView;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    @Inject
    ToDoService toDoService;
    @Inject
    MainView mainView;

    @Override
    public void onCreate(@CheckForNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView.setContentOfActivity(this);
        toDoService.loadAll();
    }
}
