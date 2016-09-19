package tech.coordinates.codereader.listener;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import tech.coordinates.codereader.service.OpenFileService;
import tech.coordinates.codereader.utility.ServicesManager;
import tech.coordinates.codereader.view.FileTextView;

/**
 * Created by Administrator on 2016/9/18.
 */
public class OnFileItemClicked implements View.OnClickListener {

    private Context context;
    private String[] str_content;
    //该service为了传递类型
    private OpenFileService open_file_service = new OpenFileService();
    private ServicesManager services_manager;
    public OnFileItemClicked(Context context){
        this.context = context;
//        Log.d("Debug",open_file_service == null?"OnFileItemClicked Null":"OnFileItemClicked Not null");
//        ServicesManager.getServicesManager(context);
//        ServicesManager.bindService(open_file_service);
    }
    public boolean isFile = false;

    /**
     * 此处为点击目录树中的文件的响应事件
     * 1.文件 启动OpenFileService，调用getFileContent(String file_path)方法
     *       在TextFragment处显示文本内容
     * @param v
     */
    @Override
    public void onClick(final View v) {
        if (isFile){
            getOpenFileService(((FileTextView)v).getCurrentPath());
        }
    }

    private void getOpenFileService(final String path){
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                open_file_service = (OpenFileService) ((OpenFileService.OpenFileBinder) service).getService();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Debug",open_file_service.getFileContent(path)[1]);
                    }
                }).start();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent i = new Intent(context,OpenFileService.class);
        context.bindService(i,conn,Service.BIND_AUTO_CREATE);
    }

}
