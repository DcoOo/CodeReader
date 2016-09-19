package tech.coordinates.codereader.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.fragment.TreeFragment;
import tech.coordinates.codereader.listener.DialogLoadFileListener;
import tech.coordinates.codereader.service.OpenFileService;
import tech.coordinates.codereader.service.TestService;
import tech.coordinates.codereader.utility.ServicesManager;

/**
 * Created by Administrator on 2016/9/17.
 */
public class ReadActivity extends AppCompatActivity implements
        TreeFragment.OnListFragmentInteractionListener{

    public static final String STR_ROOT_PATH = "path";
    public static final String STR_ROOT_NAME = "name";

    private Fragment fm_tree;
    private Bundle bundle_data;
    private Intent intent;
    private TestService service;
    private String str_path = "";
    private String str_name = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        bundle_data = new Bundle();
        intent = getIntent();
        str_path = intent.getStringExtra(DialogLoadFileListener.STR_ITEM_PATH);
        str_name = intent.getStringExtra(DialogLoadFileListener.STR_ITEM_NAME);
        bundle_data.putString(STR_ROOT_PATH,str_path);
        bundle_data.putString(STR_ROOT_NAME,str_name);
        fm_tree = getSupportFragmentManager().findFragmentById(R.id.fm_items_tree);
        ((TreeFragment)fm_tree).setBundle_data_from_read_activity(bundle_data);
        /**
         * 通过代码动态的添加目录,将控件添加到布局中
         * 该部分实现交给TreeFragment实现
         */
    }

    @Override
    public void onListFragmentInteraction(TextView item) {

    }


}
