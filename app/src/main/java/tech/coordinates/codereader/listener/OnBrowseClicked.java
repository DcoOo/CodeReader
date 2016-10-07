package tech.coordinates.codereader.listener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.adapter.ShowFilesSimpleAdapter;
import tech.coordinates.codereader.utility.FilePathUtil;

/**
 * Created by Administrator on 2016/9/20.
 */
public class OnBrowseClicked implements View.OnClickListener {

    private Context activity_setting;
    private ListView lv_show_files;
    private SimpleAdapter adapter;
    public static AlertDialog alertDialog;

    public OnBrowseClicked(Context context){
        this.activity_setting = context;
        adapter = new ShowFilesSimpleAdapter(activity_setting, FilePathUtil.getTargetDir("/"));
    }
    @Override
    public void onClick(View v) {
        View view = ((Activity)activity_setting).getLayoutInflater().inflate(R.layout.dialog_show_files,null);
        lv_show_files = (ListView) view.findViewById(R.id.lv_show_files);
        lv_show_files.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_setting);
        builder.setTitle("选择目录或文件").setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //nothing
            }
        });
        alertDialog = builder.show();
        alertDialog.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        lv_show_files.setOnItemClickListener(new ListViewFilesOnClickListener(activity_setting,alertDialog,lv_show_files));
        ((Activity) activity_setting).registerForContextMenu(lv_show_files);
    }


}
