package tech.coordinates.codereader.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

import tech.coordinates.codereader.utility.MyBinder;

public class OpenFileService extends Service {

    public static final String READ_FILE_OK = "200";
    public static final String OPEN_FILE_FAILED = "0";

    private OpenFileBinder openFileBinder = new OpenFileBinder();
    private RandomAccessFile random_file_read;

    public OpenFileService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return openFileBinder;
    }

    public class OpenFileBinder extends Binder{

//        protected OpenFileBinder(Service service) {
//            super(service);
//        }
        public Service getService(){
            return OpenFileService.this;
        }
    }

    /**
     * 使用NIO来读取
     * @param file_path 需要读取的文件路径
     * @return 一个String数组，长度为2，String[0]:状态码 String[1]:文件内容
     * @throws IOException
     */
    public String[] getFileContent(String file_path){
        String[] content = new String[2];
        random_file_read = null;
        //可以添加设置文件编码的功能CharSet实现
        StringBuilder str_builder = new StringBuilder("");
        try {
            random_file_read = new RandomAccessFile(file_path,"r");
        } catch (FileNotFoundException e) {
            Log.d("exception",file_path+":NOT EXISTS");
            content[0] = OPEN_FILE_FAILED;
            content[1] = null;
            return content;
        }
        FileChannel channel_file = random_file_read.getChannel();
        ByteBuffer buffer_byte = ByteBuffer.allocate(64);
        CharBuffer buffer_char = CharBuffer.allocate(32);
        int bytesRead = 0;
        try {
            bytesRead = channel_file.read(buffer_byte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (bytesRead != -1) {
            buffer_byte.flip();
            while (buffer_byte.hasRemaining()){
                str_builder.append((char) buffer_byte.get());
            }
            buffer_byte.clear();
            try {
                bytesRead = channel_file.read(buffer_byte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            channel_file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        content[0] = READ_FILE_OK;
        content[1] = str_builder.toString();
        return content;
    }

}
