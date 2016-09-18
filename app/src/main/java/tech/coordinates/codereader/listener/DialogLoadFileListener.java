package tech.coordinates.codereader.listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.File;

import tech.coordinates.codereader.activity.ReadActivity;
import tech.coordinates.codereader.activity.SettingActivity;
import tech.coordinates.codereader.utility.FilePathUtil;

/**
 * Created by Administrator on 2016/9/17.
 * 该类需要完成的功能：
 * 0.对文件进行过滤，如果不符合某些类型，则不能读取。
 * 1.跳转到阅读页面
 * 2.刷新ReadActivity界面，显示目录
 * 刷新操作由Activity实现
 */
public class DialogLoadFileListener implements DialogInterface.OnClickListener {

    public static final String STR_ITEM_PATH = "item_path";
    public static final String STR_ITEM_NAME = "item_name";

    private static final String regex_pattern = ".+/.[java|cpp|c|py|txt|jpg|JPG|PNG|xml]";

    private Activity activity_setting;
    private String str_item_path = "";
    private String str_item_name = "";
    public DialogLoadFileListener(Activity activity){
        this.activity_setting = activity;
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(activity_setting, ReadActivity.class);
        str_item_path = ((SettingActivity)activity_setting).getFile_path();
        str_item_name = FilePathUtil.getItemNameByPath(str_item_path,new File(str_item_path).isFile());
        intent.putExtra(STR_ITEM_PATH,str_item_path);
        intent.putExtra(STR_ITEM_NAME,str_item_name);
        activity_setting.startActivity(intent);
    }
}
