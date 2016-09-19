package tech.coordinates.codereader.utility;

import android.app.Service;
import android.os.Binder;

/**
 * Created by Administrator on 2016/9/18.
 */
public class MyBinder extends Binder {
    private Service service;

    protected MyBinder(Service service){
        this.service = service;
    }

    public Service getService(){
        return service;
    }
}
