package tech.coordinates.codereader.utility;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import tech.coordinates.codereader.R;

/**
 * Created by Administrator on 2016/9/17.'
 *
 */
public class TVLoadFileListener implements View.OnClickListener {

    private static final int CODE_REQUEST_LOAD_FILE = 0;

    private EditText et_path;
    private View dialog;
    private Button btn_browse;
    private Activity activity_setting;
    public TVLoadFileListener(Activity activity,View dialog){
        this.activity_setting = activity;
        this.dialog = dialog;
    }
    @Override
    public void onClick(View view) {
        final View dialog = LayoutInflater.from(activity_setting).inflate(R.layout.dialog_load_file,null);
        et_path = (EditText) dialog.findViewById(R.id.et_dialog_load_path);
        btn_browse = (Button) dialog.findViewById(R.id.btn_dialog_browse);
        //点击浏览之后启动android系统的资源管理器，选中文件之后返回文件path
        btn_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                activity_setting.startActivityForResult(intent,CODE_REQUEST_LOAD_FILE);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_setting);
        builder.setTitle(R.string.load_file).setView(dialog).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //点击确定之后按照ET中的path处理文件
            }
        }).show();
    }

}
