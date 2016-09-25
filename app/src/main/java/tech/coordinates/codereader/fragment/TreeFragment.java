package tech.coordinates.codereader.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.activity.ReadActivity;
import tech.coordinates.codereader.exception.LayoutNotFoundException;
import tech.coordinates.codereader.listener.OnDirectoryItemClicked;
import tech.coordinates.codereader.listener.OnFileItemClicked;
import tech.coordinates.codereader.view.DirectoryTextView;
import tech.coordinates.codereader.view.FileTextView;

/**
 * Created by Administrator on 2016/9/13.
 * 该Fragment是用于显示CodeReader左侧的目录树
 * 首先在进入到ReadActivity时，显示根目录/文件
 * 当点击目录，重新加载Fragment，显示其下的子目录或文件
 * 当点击文件，在TextFragment加载内容
 */
public class TreeFragment extends Fragment {

    public static final int FILE_COLOR = Color.BLUE;
    public static final int DIRECTORY_UNOPENED_COLOR = Color.GREEN;
    public static final int DIRECTORY_OPENED_COLOR = Color.BLACK;

    private ViewGroup.LayoutParams params_ftv = new ViewGroup.LayoutParams
            (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

    public static TextView getTv_root() {
        return tv_root;
    }

    private static TextView tv_root;
    private static LinearLayout ll_items_tree_main;
    /**
     * 添加进该Fragment的每个View的监听器
     */
    private View.OnClickListener file_listener;
    private Bundle bundle_data_from_read_activity;
    private TextView tv_file;

    public void setBundle_data_from_read_activity(Bundle bundle_data_from_read_activity) {
        this.bundle_data_from_read_activity = bundle_data_from_read_activity;
    }

    public static LinearLayout getTreeLinearLayout() {
        return ll_items_tree_main;
    }

    private static String str_root_item_path = null;

    public static String getStr_root_item_path() {
        return str_root_item_path;
    }

    public static String getStr_root_item_name() {
        return str_root_item_name;
    }

    private static String str_root_item_name = null;

    //在创建目录树的过程中使用
    private FileTextView ftv_file;
    private DirectoryTextView dtv_directory;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tree,container,false);
        ll_items_tree_main = (LinearLayout) view.findViewById(R.id.ll_fm_tree_main);
        if (ll_items_tree_main == null){
            try {
                throw new LayoutNotFoundException();
            } catch (LayoutNotFoundException e) {
                e.printStackTrace();
            }
        }
        file_listener = new OnFileItemClicked(this.getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (bundle_data_from_read_activity != null){
            str_root_item_path = bundle_data_from_read_activity.getString(ReadActivity.STR_ROOT_PATH);
            str_root_item_name = bundle_data_from_read_activity.getString(ReadActivity.STR_ROOT_NAME);
        }
        Log.d("Debug","TreeFragment onActivityCreated");
        tv_root = (TextView) addView(str_root_item_name,new File(str_root_item_path));
        ll_items_tree_main.addView(tv_file);
    }

    public View addView(String name, File file){
        if (file.isFile()){
            tv_file = new FileTextView(this.getContext(),null,FILE_COLOR,0);
            tv_file.setLayoutParams(params_ftv);
            tv_file.append(name);
            ((FileTextView) tv_file).setCurrentPath(file.getPath());
            tv_file.setOnClickListener(file_listener);
            //啊！！！！！！！！！以后一定先设计好 改好麻烦 - -   一定要多一个父类
        }else {
            //Directory Into
            tv_file = new DirectoryTextView(this.getContext(),null,DIRECTORY_UNOPENED_COLOR,false);
            tv_file.setLayoutParams(params_ftv);
            tv_file.setText("    "+name);
            ((DirectoryTextView) tv_file).setCurrentPath(file.getPath());
            Log.d("Debug",file.getPath());
            //Add Listener
            tv_file.setOnClickListener(new OnDirectoryItemClicked(TreeFragment.this));
        }
        //在该方法体中还应该为每一个View添加监听事件
        return tv_file;
    }

    public void setStr_root_item_path(String str_root_item_path) {
        this.str_root_item_path = str_root_item_path;
    }

    public void setStr_root_item_name(String str_root_item_name) {
        this.str_root_item_name = str_root_item_name;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(TextView item);
    }

}
