package at.htl.leonding;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.compose.ui.platform.ComposeView;
import at.htl.leonding.ui.layout.MainViewKt;

public class MainActivity extends ComponentActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var view = new ComposeView(this);
        MainViewKt.fillMainViewContent(view);
        setContentView(view);
    }
}
