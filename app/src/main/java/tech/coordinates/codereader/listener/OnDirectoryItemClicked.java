package tech.coordinates.codereader.listener;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import tech.coordinates.codereader.fragment.TreeFragment;
import tech.coordinates.codereader.utility.FilePathUtil;
import tech.coordinates.codereader.view.DirectoryTextView;
import tech.coordinates.codereader.view.FileTextView;

/**
 * Created by Administrator on 2016/9/21.
 */
public class OnDirectoryItemClicked implements View.OnClickListener {

    private static LinearLayout ll_tree_main = TreeFragment.getTreeLinearLayout();
    private TreeFragment fragment;

    public OnDirectoryItemClicked(TreeFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onClick(View v) {
        if (((DirectoryTextView)v).isOpened){
            //目前是打开状态，再次点击时关闭Directory,同时将其子孩子在fragment中删除
            ((DirectoryTextView) v).isOpened = false;
            ((DirectoryTextView) v).setPaint_color(TreeFragment.DIRECTORY_UNOPENED_COLOR);
            v.invalidate();
            //删除当前目录下的所有子文件
            Log.d("Debug", "Directory open");
        }else {
            //目前是未打开状态，再次点击时打开directory，然后显示其子孩子
            ((DirectoryTextView) v).isOpened = true;
            ((DirectoryTextView) v).setPaint_color(TreeFragment.DIRECTORY_OPENED_COLOR);
            v.invalidate();
            //列出当前目录下的所有子文件
            addChildFiles(v);
            Log.d("Debug", "Directory open");
        }

    }

    private void addChildFiles(View view){
        File[] files = FilePathUtil.getFileList(new File(((DirectoryTextView)view).getCurrentPath()));
        if (files == null){
            return ;
        }
        for (File f : files){
            String path = f.getPath();
            ll_tree_main.addView(fragment.addView(path,FilePathUtil.getItemNameByPath(path),f));

        }
    }
}
