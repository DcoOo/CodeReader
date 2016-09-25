package tech.coordinates.codereader.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.LinearLayout;
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
import tech.coordinates.codereader.view.DirectoryTextView;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ll_main;
    private DirectoryTextView dtv;
    private int i = 0;
    private LinearLayout ll;
    private DirectoryTextView dtv_root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

    }

    @Override
    public void onClick(View v) {
        showChildDirectry(v,"child1");
        showChildDirectry(v,"child2");
    }

    public void showChildDirectry(View parent,String name){
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ((LinearLayout)parent.getParent()).addView(ll);
        dtv = new DirectoryTextView(this,null);
        dtv.setText("    "+name);
        dtv.setOnClickListener(this);
        ll.addView(dtv);
    }
}
