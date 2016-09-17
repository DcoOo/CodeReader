package tech.coordinates.codereader.listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import tech.coordinates.codereader.activity.ReadActivity;
import tech.coordinates.codereader.activity.SettingActivity;

/**
 * Created by Administrator on 2016/9/17.
 * 该类需要完成的功能：
 * 1.跳转到阅读页面
 * 2.刷新ReadActivity界面，显示目录
 *
 */
public class DialogLoadFileListener implements DialogInterface.OnClickListener {
    public static final String STR_PATH = "path";
    private Activity activity_setting;
    private String str_path;
    public DialogLoadFileListener(Activity activity){
        this.activity_setting = activity;
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(activity_setting, ReadActivity.class);
        str_path = ((SettingActivity)activity_setting).getFile_path();
        intent.putExtra(STR_PATH,str_path);
        activity_setting.startActivity(intent);
    }
}
