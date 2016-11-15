package tech.coordinates.codereader.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.LinkedList;

import tech.coordinates.codereader.utility.EncodeManager;
import tech.coordinates.codereader.utility.FileNameFilter;
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
    public LinkedList<String> getFileContent(String file_path) throws IOException {
        /**
         * 对文件名进行过滤。不符合的文件暂时无法打开
         */
        if (!isAccept(file_path)){
            return null;
        }
        /**
         * 保留插入对编码的处理，按照文本的编码格式来打开文本，否则会出现乱码
         */
        LinkedList<String> content = new LinkedList<>();
        File f = new File(file_path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String line ;
        while ((line = reader.readLine())!=null){
            content.add(line);
        }
//        Charset charset = Charset.forName(EncodeManager.getFileEncoding(file_path));
//        Log.d("Debug",EncodeManager.getFileEncoding(file_path));
//        CharsetDecoder decoder = charset.newDecoder();
//        random_file_read = null;
//        //可以添加设置文件编码的功能CharSet实现
//        StringBuilder str_builder = new StringBuilder("");
//        try {
//            random_file_read = new RandomAccessFile(file_path,"r");
//        } catch (FileNotFoundException e) {
//            Log.d("exception",file_path+":NOT EXISTS");
//            content[0] = OPEN_FILE_FAILED;
//            content[1] = null;
//            return content;
//        }
//        FileChannel channel_file = random_file_read.getChannel();
//        ByteBuffer buffer_byte = ByteBuffer.allocate(2048);
//        //CharBuffer一定要设计的够大，和Byte Buffer一样大就可以，因为编码的问题，Char对应几个bytes不一定
//        CharBuffer buffer_char = CharBuffer.allocate(2048);
//        int bytesRead = 0;
//        try {
//            bytesRead = channel_file.read(buffer_byte);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        while (bytesRead != -1) {
//            buffer_byte.flip();
//            decoder.decode(buffer_byte,buffer_char,false);
//            buffer_char.flip();
//            while (buffer_char.hasRemaining()){
//                str_builder.append(buffer_char.get());
//            }
//            buffer_byte.clear();
//            buffer_char.clear();
//            try {
//                bytesRead = channel_file.read(buffer_byte);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            channel_file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        content[0] = READ_FILE_OK;
//        content[1] = str_builder.toString();
        return content;
    }

    private boolean isAccept(String path){
        return new FileNameFilter().accept(null,path);
    }

}
