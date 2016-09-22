package tech.coordinates.codereader.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.listener.DialogLoadFileListener;
import tech.coordinates.codereader.listener.OnBrowseClicked;
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
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TVLoadFileListener.CODE_REQUEST_LOAD_FILE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            et_path = TVLoadFileListener.getEt_path();
            et_path.setText("");
            this.file_path = FilePathUtil.getPathByUri4kitkat(this,uri);
            et_path.setText(file_path);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem menuItem1 = menu.add(1,1,1,"选择该文件");
        menuItem1.setOnMenuItemClickListener(new MyOnMenuItemSelected());

    }

    class MyOnMenuItemSelected implements MenuItem.OnMenuItemClickListener{
        //按后退之后再次点击没有调用该方法
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Log.d("Debug","Context Listener");
            View itemView;
            int id = item.getItemId();
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            itemView = info.targetView;
            switch (id){
                case 1:
                    getDirectory(itemView);
                    OnBrowseClicked.alertDialog.dismiss();
                    break;
            }
            return true;
        }
    }

    public void getDirectory(View view){
        String name = ((TextView)view.findViewById(R.id.tv_item_name)).getText().toString();
        String path = ((TextView)view.findViewById(R.id.tv_item_path)).getText().toString();
        DialogLoadFileListener.setStr_item_name(name);
        DialogLoadFileListener.setStr_item_path(path);
        et_path = TVLoadFileListener.getEt_path();
        et_path.setText("");
        et_path.setText(path);
    }
}

