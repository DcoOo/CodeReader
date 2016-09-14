package tech.coordinates.codereader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.coordinates.codereader.R;

/**
 * Created by Administrator on 2016/9/13.
 * 该Fragment是用于显示CodeReader左侧的目录树
 */
public class TreeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tree,container,false);
    }
}
