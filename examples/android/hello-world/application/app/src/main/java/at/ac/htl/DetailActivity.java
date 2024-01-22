package at.ac.htl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        TextView editText = findViewById(R.id.textLabel);
        editText.setText("Hallo world2!");
    }
}