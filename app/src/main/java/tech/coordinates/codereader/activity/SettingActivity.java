package tech.coordinates.codereader.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.utility.TVLoadFileListener;

public class SettingActivity extends AppCompatActivity {
    private View tv_load_file;
    private View dialog_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tv_load_file = findViewById(R.id.tv_load_file);
        //首先进行加载Dialog将View作为参数传给监听器
        dialog_view = getLayoutInflater().from(this).inflate(R.layout.dialog_load_file,null);
        tv_load_file.setOnClickListener(new TVLoadFileListener(this,dialog_view));
        EditText et = (EditText) findViewById(R.id.et_dialog_load_path);
        et.setText("Hello world");
    }

}
