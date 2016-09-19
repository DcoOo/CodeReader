package tech.coordinates.codereader.utility;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import tech.coordinates.codereader.service.OpenFileService;

/**
 * Created by DcoOo on 2016/9/18.
 * 将Activity对于Service的管理解耦，
 * 通过Manager类来进行管理，
 * 提供了绑定服务，得到服务，解除绑定等静态操作
 */
public class ServicesManager {

    private static ServicesManager servicesManager;
    private static OpenFileService open_file_service;
    private static Intent intent_bind;//for bind
    private static Context context;

    private ServicesManager(Context context){
        this.context = context;
    }

    public  static ServicesManager getServicesManager(Context context){
        if (servicesManager == null){
            servicesManager = new ServicesManager(context);
        }
        return servicesManager;
    }

    public static void bindService(Service service,ServiceConnection conn){
        intent_bind = new Intent(context,service.getClass());
        Log.d("Debug",context == null?"ServicesManager Context Null":"ServicesManager Context Not Null");
        context.bindService(intent_bind,conn,Service.BIND_AUTO_CREATE);
    }

    public static void unbindService(Service service,ServiceConnection conn){
        context.unbindService(conn);
    }

}
