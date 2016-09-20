package tech.coordinates.codereader.listener;

import android.app.Activity;
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

    public static final int CODE_REQUEST_LOAD_FILE = 0;

    private View dialog;
    private Button btn_browse;
    private Activity activity_setting;
    private static EditText et_path;

    public TVLoadFileListener(Activity activity){
        this.activity_setting = activity;
    }

    public static EditText getEt_path() {
        return et_path;
    }

    @Override
    public void onClick(View view) {
        dialog = LayoutInflater.from(activity_setting).inflate(R.layout.dialog_load_file,null);
        et_path = (EditText) dialog.findViewById(R.id.et_dialog_load_path);

        btn_browse = (Button) dialog.findViewById(R.id.btn_dialog_browse);
        //点击浏览之后启动android系统的资源管理器，选中文件之后返回文件path
//        btn_browse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");
//                activity_setting.startActivityForResult(intent,CODE_REQUEST_LOAD_FILE);
//            }
//        });
        btn_browse.setOnClickListener(new OnBrowseClicked(activity_setting));
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity_setting);
        builder.setTitle(R.string.load_file).setView(dialog).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //  nothing
            }
        }).setPositiveButton(R.string.sure, new DialogLoadFileListener(activity_setting)).show();
    }

}
