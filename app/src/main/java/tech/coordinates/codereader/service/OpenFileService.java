package tech.coordinates.codereader.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class OpenFileService extends Service {

    private static final String READ_FILE_OK = "200";
    private static final String OPEN_FILE_FAILED = "0";

    private RandomAccessFile random_file_read;
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

    /**
     * 使用NIO来读取
     * @param file_path 需要读取的文件路径
     * @return 一个String数组，长度为2，String[0]:状态码 String[1]:文件内容
     * @throws IOException
     */
    public String[] getFileContent(String file_path) throws IOException {
        String[] content = new String[2];
        RandomAccessFile file = null;
        StringBuilder str_builder = new StringBuilder("");
        try {
            file = new RandomAccessFile(file_path,"r");
        } catch (FileNotFoundException e) {
            Log.d("exception",file_path+":NOT EXISTS");
            content[0] = OPEN_FILE_FAILED;
            content[1] = null;
            return content;
        }
        FileChannel channel_file = file.getChannel();
        ByteBuffer buffer_byte = ByteBuffer.allocate(64);
        int bytesRead = channel_file.read(buffer_byte);
        while (bytesRead != -1) {
            str_builder.append((char) buffer_byte.get());
        }
        content[0] = READ_FILE_OK;
        content[1] = str_builder.toString();
        return content;
    }

}
