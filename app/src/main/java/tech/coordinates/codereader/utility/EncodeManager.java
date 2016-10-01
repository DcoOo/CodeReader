package tech.coordinates.codereader.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/**
 * Created by Administrator on 2016/9/24.
 * 该类用于管理所有文本的编码
 * 提供的方法如下：
 * 1.得到文件的编码方式
 * 2.判断能否用指定的编码方式进行解码
 * 3.对指定的文件按照指定的编码进行保存
 */
public class EncodeManager {

    private static String[] ENCODING = new String[]{"utf-8","GBK","unicode","GB2312","iso-8859-1","iso-8859-2"};
    private static FileInputStream file;

    public static String getFileEncoding(String path){
        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String encode = null;
        for (String aENCODING : ENCODING) {
            try {
                if (canDecode(file, Charset.forName(aENCODING))) {
                    return aENCODING;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encode;
    }

    private static boolean canDecode(InputStream input, Charset charset) throws IOException {
        ReadableByteChannel channel = Channels.newChannel(input);
        CharsetDecoder decoder = charset.newDecoder();

        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        boolean endOfInput = false;
        while (!endOfInput) {
            int n = channel.read(byteBuffer);
            byteBuffer.flip(); // flip so it can be drained

            endOfInput = (n == -1);
            CoderResult coderResult = decoder.decode(byteBuffer, charBuffer, endOfInput);
            charBuffer.clear();
            if (coderResult == CoderResult.OVERFLOW) {
                while (coderResult == CoderResult.OVERFLOW) {
                    coderResult = decoder.decode(byteBuffer, charBuffer, endOfInput);
                    charBuffer.clear();
                }
            }
            if (coderResult.isError()) {
                return false;
            }
            byteBuffer.compact(); // compact so it can be refilled
        }
        CoderResult coderResult;
        while ((coderResult = decoder.flush(charBuffer)) == CoderResult.OVERFLOW) {
            charBuffer.clear();
        }
        if (coderResult.isError()) {
            return false;
        }

        return true;
    }
}
