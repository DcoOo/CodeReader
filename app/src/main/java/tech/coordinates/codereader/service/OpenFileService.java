package tech.coordinates.codereader.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;

public class OpenFileService extends Service {

    private static final String READ_FILE_OK = "200";
    private static final String OPEN_FILE_FAILED = "0";

    private OpenFileBinder openFileBinder = new OpenFileBinder();
    public OpenFileService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return openFileBinder;
    }

    public class OpenFileBinder extends Binder{
        Service getService(){
            return OpenFileService.this;
        }
    }

    public String[] getFileContent(String file_path){
        String[] content = new String[2];
        File file = new File(file_path);
        if (!file.exists()){
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                Log.d("exception",file_path+":NOT EXISTS");
                content[0] = OPEN_FILE_FAILED;
                content[1] = null;
                return content;
            }
        }


    }

}
