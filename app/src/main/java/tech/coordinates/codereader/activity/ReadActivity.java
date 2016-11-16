package tech.coordinates.codereader.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.fragment.ContentFragment;
import tech.coordinates.codereader.fragment.TreeFragment;
import tech.coordinates.codereader.listener.DialogLoadFileListener;

/**
 * Created by Administrator on 2016/9/17.
 */
public class ReadActivity extends AppCompatActivity implements
        TreeFragment.OnListFragmentInteractionListener {

    public static final String STR_ROOT_PATH = "path";
    public static final String STR_ROOT_NAME = "name";
    public static final String SPAN_STR_HL = "SPANSTR";

    private static Handler handler_main = null;
    private static LinkedList<String> file_content;

    private Button btn_back;
    private android.support.v4.app.Fragment fm_tree;
    private Bundle bundle_data;
    private Intent intent;
    private String str_path = "";
    private String str_name = "";
    private Fragment fragment_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        bundle_data = new Bundle();
        intent = getIntent();
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        str_path = intent.getStringExtra(DialogLoadFileListener.STR_ITEM_PATH);
        str_name = intent.getStringExtra(DialogLoadFileListener.STR_ITEM_NAME);
        bundle_data.putString(STR_ROOT_PATH, str_path);
        bundle_data.putString(STR_ROOT_NAME, str_name);
        fm_tree = getSupportFragmentManager().findFragmentById(R.id.fm_items_tree);
        ((TreeFragment) fm_tree).setBundle_data_from_read_activity(bundle_data);
        /**
         * 通过代码动态的添加目录,将控件添加到布局中
         * 该部分实现交给TreeFragment实现
         */
        handleFileContent();

    }

    public static LinkedList<String> getFile_content() {
        return file_content;
    }

    /**
     * 得到文件的内容是在子线程中得到，所以需要使用Handler Looper机制来实现线程间的通信
     */
    private void handleFileContent() {
        handler_main = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                file_content = (LinkedList<String>) msg.obj;
                Log.d("Debug", "Main handler" + file_content);
                showInTextFragment();
            }
        };
    }

    private void showInTextFragment() {
        Bundle argument = new Bundle();
        /**
         *保留对content处理，使用SpannableString进行代码高亮的处理
         */
        argument.putSerializable("content", file_content);
        fragment_content = new ContentFragment();
        fragment_content.setArguments(argument);
        getFragmentManager().beginTransaction().replace(R.id.fm_file_content, fragment_content).commit();
    }

    @Override
    public void onListFragmentInteraction(TextView item) {

    }

    public static Handler getHandler_main() {
        return handler_main;
    }
}
