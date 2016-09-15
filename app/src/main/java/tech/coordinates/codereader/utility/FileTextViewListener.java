package tech.coordinates.codereader.utility;

import android.view.View;

import tech.coordinates.codereader.view.FileTextView;

/**
 * Created by Administrator on 2016/9/15.
 */
public class FileTextViewListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        String current_path = ((FileTextView) view).getCurrentPath();

    }
}
