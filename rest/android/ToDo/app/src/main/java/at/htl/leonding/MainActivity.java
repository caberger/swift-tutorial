package at.htl.leonding;

import android.os.Bundle;

import org.eclipse.microprofile.config.Config;

import javax.inject.Inject;

import androidx.activity.ComponentActivity;
import at.htl.leonding.model.ToDoService;
import at.htl.leonding.ui.layout.MainView;
import dagger.hilt.android.AndroidEntryPoint;

/** Our main activity implemented in java.
 * We separate the application logic from the view. This is our controller.
 * The View in implemented in Jetpack compose in a separate file (separation of concerns).
 * For the views we use Kotlin, then we still have a nice Design view.
 */
@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    @Inject
    ToDoService toDoService;
    @Inject
    MainView mainView;

    @Inject
    Config configuration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView.setContentOfActivity(this);
        toDoService.loadAll();
    }
}
