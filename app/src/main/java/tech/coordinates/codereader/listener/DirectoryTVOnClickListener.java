package tech.coordinates.codereader.listener;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import tech.coordinates.codereader.view.DirectoryTextView;

/**
 * Created by Administrator on 2016/9/17.
 */
public class DirectoryTVOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        //目录被打开
        if (!((DirectoryTextView) view).isOpened) {

        } else {
            ((DirectoryTextView) view).isOpened = false;
            ((DirectoryTextView) view).setPaint_color(Color.parseColor("#FF34B3"));
            view.invalidate();
            Log.d("Debug", "Directory close");
        }
    }
}
