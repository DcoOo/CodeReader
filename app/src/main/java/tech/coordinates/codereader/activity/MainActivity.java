package tech.coordinates.codereader.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.view.OpenedDirTextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenedDirTextView dirTV = (OpenedDirTextView) findViewById(R.id.file_test);
        dirTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OpenedDirTextView)view).setPaint_color(Color.BLACK);
//                Log.d("Debug","Clicked");
                ((OpenedDirTextView) view).invalidate();
            }
        });
    }
}
