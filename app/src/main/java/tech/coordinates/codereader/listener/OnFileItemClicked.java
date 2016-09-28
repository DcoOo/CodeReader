package tech.coordinates.codereader.listener;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import tech.coordinates.codereader.activity.ReadActivity;
import tech.coordinates.codereader.service.OpenFileService;
import tech.coordinates.codereader.view.FileTextView;

/**
 * Created by Administrator on 2016/9/18.
 */
public class OnFileItemClicked implements View.OnClickListener {


    //该service为了传递类型
    private static OpenFileService open_file_service = new OpenFileService();
    private static ServiceConnection conn;

    private Context context;
    private String[] str_content;
    private Handler handler_main;
    private String content;
    private Fragment fragment_content;

    private boolean isOpenFileServiceBinded = false;

    public OnFileItemClicked(Context context) {
        this.context = context;
    }

    static {
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                open_file_service = (OpenFileService) ((OpenFileService.OpenFileBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
    }

    /**
     * 此处为点击目录树中的文件的响应事件
     * 1.文件 启动OpenFileService，调用getFileContent(String file_path)方法
     * 在TextFragment处显示文本内容
     *
     * @param v
     */
    @Override
    public void onClick(final View v) {
        final String path = ((FileTextView) v).getCurrentPath();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(context,OpenFileService.class);
                if (!isOpenFileServiceBinded){
                    context.bindService(i,conn,Context.BIND_AUTO_CREATE);
                    isOpenFileServiceBinded = true;
                }
                str_content = open_file_service.getFileContent(path);
                if (str_content[0].equals(OpenFileService.READ_FILE_OK)) {
                    //向主线程发送数据
                    Looper.prepare();
                    handler_main = ReadActivity.getHandler_main();
                    Message msg = handler_main.obtainMessage();
                    msg.obj = str_content[1];
                    Log.d("Debug", "Listener" + str_content[1]);
                    handler_main.sendMessage(msg);
                    Looper.loop();
                }

            }
        }).start();
    }

}
