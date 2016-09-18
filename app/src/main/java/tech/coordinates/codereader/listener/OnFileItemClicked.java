package tech.coordinates.codereader.listener;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tech.coordinates.codereader.view.FileTextView;

/**
 * Created by Administrator on 2016/9/18.
 */
public class OnFileItemClicked implements View.OnClickListener {

    public boolean isFile = false;

    /**
     * 此处为点击目录树中的文件的响应事件
     * 1.文件 启动OpenFileService，调用getFileContent(String file_path)方法
     *       在TextFragment处显示文本内容
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (isFile){
            FileTextView ftv = (FileTextView) v;
            Log.d("Debug",ftv.getCurrentPath());
        }
    }
}
