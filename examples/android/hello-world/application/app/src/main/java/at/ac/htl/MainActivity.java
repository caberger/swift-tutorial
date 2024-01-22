package at.ac.htl;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.activity.ComponentActivity;
import at.ac.htl.model.Router;
import at.ac.htl.model.Store;
import at.ac.htl.ui.layout.MainActivityLayout;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    @Inject
    Store store;
    @Inject
    MainActivityLayout mainActivityLayout;
    @Inject
    Router router;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityLayout.setContentViewFor(this);

        store.subject.distinctUntilChanged().subscribe(model -> System.out.println("model changed"));
        store.produce(model -> model.greeting = "huh");
    }
}
