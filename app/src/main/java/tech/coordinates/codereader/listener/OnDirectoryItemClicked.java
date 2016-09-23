package tech.coordinates.codereader.listener;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
    //不要随便的定义static变量！！！！！！！！！！
    private LinearLayout ll_tree_main;
    private TreeFragment fragment;
    private LinearLayout ll;
    private DirectoryTextView dtv;
    private FileTextView ftv;
    private OnFileItemClicked file_listener;
    private ViewGroup.LayoutParams params_ftv = new ViewGroup.LayoutParams
            (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

    public OnDirectoryItemClicked(TreeFragment fragment){
        this.fragment = fragment;
        ll_tree_main = TreeFragment.getTreeLinearLayout();
    }

    @Override
    public void onClick(View v) {
        if (((DirectoryTextView)v).isOpened){
            //目前是打开状态，再次点击时关闭Directory,同时将其子孩子在fragment中删除
            ((DirectoryTextView) v).isOpened = false;
            ((DirectoryTextView) v).setPaint_color(TreeFragment.DIRECTORY_UNOPENED_COLOR);
            v.invalidate();
            //删除当前目录下的所有子文件
        }else {
            //目前是未打开状态，再次点击时打开directory，然后显示其子孩子
            ((DirectoryTextView) v).isOpened = true;
            ((DirectoryTextView) v).setPaint_color(TreeFragment.DIRECTORY_OPENED_COLOR);
            v.invalidate();
            //列出当前目录下的所有子文件
            showChildFiles(v);
        }

    }
    public void showChildFiles(View parent){
        File[] files = FilePathUtil.getFileList(new File(((DirectoryTextView)parent).getCurrentPath()));
        if (files == null){
            return ;
        }
        for (File f : files){
            if (f.isFile()){
                int stages = FilePathUtil.getNumberOfFlow(TreeFragment.getStr_root_item_path(),f.getPath());
                Log.d("Debug","File:"+stages);
                ftv = new FileTextView(fragment.getContext(),null,stages);
                ftv.setLayoutParams(params_ftv);
                ftv.setCurrentPath(f.getPath());
                ftv.append(f.getName());
                ((LinearLayout)parent.getParent()).addView(ftv);
                file_listener = new OnFileItemClicked(fragment.getContext());
                ftv.setOnClickListener(file_listener);
            }
            else {
                ll = new LinearLayout(fragment.getContext());
                ll.setOrientation(LinearLayout.VERTICAL);
                ((LinearLayout)parent.getParent()).addView(ll);
                int stages = FilePathUtil.getNumberOfFlow(TreeFragment.getStr_root_item_path(),f.getPath());
                dtv = new DirectoryTextView(fragment.getContext(),null,TreeFragment.DIRECTORY_UNOPENED_COLOR,Color.BLACK,false,stages);
                dtv.append(f.getName());
                dtv.setCurrentPath(f.getPath());
                dtv.setOnClickListener(this);
                ll.addView(dtv);
            }
        }


    }
}
