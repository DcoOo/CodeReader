package tech.coordinates.codereader.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.adapter.ShowFilesSimpleAdapter;
import tech.coordinates.codereader.utility.FilePathUtil;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ListViewFilesOnClickListener implements AdapterView.OnItemClickListener {

    public static final String STR_ITEM_PATH = "item_path";
    public static final String STR_ITEM_NAME = "item_name";

    private Context context;
    private EditText et_path;
    private TextView tv_name;
    private TextView tv_path;
    private TextView tv_isFile;
    private String str_name;
    private String str_path;
    private boolean boo_isFile;
    private AlertDialog dialog;
    private ListView lv;
    private SimpleAdapter adapter;

    public ListViewFilesOnClickListener(Context context, AlertDialog dialog, ListView lv){
        this.context = context;
        this.dialog = dialog;
        this.lv = lv;
        ((Activity)context).registerForContextMenu(lv);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tv_name = (TextView) view.findViewById(R.id.tv_item_name);
        tv_path = (TextView) view.findViewById(R.id.tv_item_path);
        tv_isFile = (TextView) view.findViewById(R.id.tv_item_isFile);
        str_name = tv_name.getText().toString();
        str_path = tv_path.getText().toString();
        boo_isFile = tv_isFile.getText().toString().equals(DialogLoadFileListener.FILE);

        //如果点击的是文件
        if (boo_isFile){
            onClickFile(str_name,str_path);
        }else {
            //点击目录
            onClickDirectory(str_name,str_path);
        }
    }

    private void onClickFile(String name,String path){
        et_path = TVLoadFileListener.getEt_path();
        et_path.setText(path);
        //将数据传到DialogLoadFileListener中
        DialogLoadFileListener.setStr_item_name(name);
        DialogLoadFileListener.setStr_item_path(path);
        dialog.dismiss();
    }

    private void onClickDirectory(String name,String path){
        //刷新界面，进入到下一级目录
        if (FilePathUtil.getTargetDir(path) != null){
            adapter = new ShowFilesSimpleAdapter(context, FilePathUtil.getTargetDir(path));
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(this);
        }

    }

}
