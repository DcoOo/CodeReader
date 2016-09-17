package tech.coordinates.codereader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.listener.DialogLoadFileListener;

/**
 * Created by Administrator on 2016/9/17.
 */
public class ReadActivity extends AppCompatActivity {
    private Intent intent;
    private String str_root_path;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        intent = getIntent();
        str_root_path = intent.getStringExtra(DialogLoadFileListener.STR_PATH);
    }
}
