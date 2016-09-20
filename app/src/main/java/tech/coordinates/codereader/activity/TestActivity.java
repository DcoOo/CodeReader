package tech.coordinates.codereader.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.listener.ListViewFilesOnClickListener;
import tech.coordinates.codereader.service.TestService;
import tech.coordinates.codereader.utility.FilePathUtil;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TestActivity extends AppCompatActivity{
    private MenuItem menuItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        Button btn = (Button) findViewById(R.id.btn_show_files);
        registerForContextMenu(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                AlertDialog.Builder dialog;
                View view = getLayoutInflater().inflate(R.layout.dialog_load_file,null);
                Button btn = (Button) view.findViewById(R.id.btn_dialog_browse);
                TestActivity.this.registerForContextMenu(btn);
                dialog = builder.setView(view);
                dialog.show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        getMenuInflater().inflate(R.menu.menu_context_dir,menu);
        MenuItem item1 = menu.add(1,1,1,"选择文件夹");
        item1.setOnMenuItemClickListener(new MyOnMenuItemClicked());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("Debug","Here!!");
        return super.onContextItemSelected(item);
    }

    class MyOnMenuItemClicked implements MenuItem.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Log.d("Debug","Here!!!!");
            return false;
        }
    }
}
