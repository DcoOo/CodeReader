package tech.coordinates.codereader.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.MainThread;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import tech.coordinates.codereader.activity.MainActivity;
import tech.coordinates.codereader.utility.FileNameFilter;

public class LoadFileService extends Service {

    public static final String WITH_CHILD_FILES = "200";
    public static final String WITHOUT_CHILD_FILES = "201";
    public static final String FILE_NOT_FOUND = "404";


    private LoadFileBinder load_file = new LoadFileBinder();
    public LoadFileService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return load_file;
    }

    public class LoadFileBinder extends Binder{
        Service getService(){
            return LoadFileService.this;
        }
    }
    /**
     * 传过来所要加载的文件夹路径
     * 其中判断属于文件还是文件夹在MainActivity中进行
     * 如果是文件夹，返回一个List保存着该目录下的文件路径
     */
    public List<String> getChildFiles(String directory_path){
        List<String> list_sub_files = new LinkedList<>();
        File dir_file = new File(directory_path);
        if (!dir_file.exists()){
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                Log.d("exception",directory_path+": 不存在");
                list_sub_files.add(FILE_NOT_FOUND);
                return list_sub_files;
            }
        }
        /**
         * 这里是返回File比较好还是返回Path？？
         * 应该直接返回Path，因为我这里处理只是为了在点击目录时，展示其子目录和子文件
         * 只需要路径就够了，具体的打开文件操作由OpenFileService完成
         */
        String[] files_name =
    }
}
