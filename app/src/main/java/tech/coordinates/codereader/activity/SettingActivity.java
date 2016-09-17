package tech.coordinates.codereader.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.listener.TVLoadFileListener;
import tech.coordinates.codereader.utility.FilePathUtil;

public class SettingActivity extends AppCompatActivity {

    private String file_path;
    private View tv_load_file;
    private EditText et_path;
    private TVLoadFileListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tv_load_file = findViewById(R.id.tv_load_file);
        listener = new TVLoadFileListener(this);
        tv_load_file.setOnClickListener(listener);
    }

    public String getFile_path() {
        return file_path;
    }

    /**
     * 调用android自带的文件管理器，选择文件，将文件路径输入到EditText中。
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TVLoadFileListener.CODE_REQUEST_LOAD_FILE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            et_path = listener.getEt_path();
            et_path.setText("");
            this.file_path = FilePathUtil.getPathByUri4kitkat(this,uri);
            et_path.setText(file_path);
        }
    }

}

