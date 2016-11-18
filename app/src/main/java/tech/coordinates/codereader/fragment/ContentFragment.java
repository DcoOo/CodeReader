package tech.coordinates.codereader.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.structure.FileStructure;
import tech.coordinates.codereader.structure.HighLightUtil;
import tech.coordinates.codereader.structure.Row;
import tech.coordinates.codereader.structure.RowProperty;
import tech.coordinates.codereader.structure.Word;
import tech.coordinates.codereader.structure.WordProperty;
import tech.coordinates.codereader.utility.SpannableUtility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "content";
    ArrayList<LinkedList<String>> array_content;
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private LinkedList<String> linkedlist_content;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            linkedlist_content = (LinkedList<String>) getArguments().getSerializable(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        TextView tv_content = (TextView) view.findViewById(R.id.test_tv);
        SpannableString span = null;
        if (linkedlist_content != null) {
            //创建文件结构，由行、列、词组成
            FileStructure fileStructure = new FileStructure(linkedlist_content);
            //对每行进行分析，为所有的词添加属性
            HighLightUtil.initFileStructure(fileStructure);
            //每一行都有一个HashMap，存放每一个word在该行的位置（开始、结束）,word的属性
            HighLightUtil.highLight(fileStructure);
            //将每一行高亮后的结果拼接
//            SpannableStringBuilder span_builder = new SpannableStringBuilder();
            for (Row row : fileStructure.getRow_array_list()){
//                if (!row.toString().equals("") && row.getRowProperty() != RowProperty.ROW_PROPERTY_MULTI_LINE_COMMENT && row.getRowProperty() != RowProperty.ROW_PROPERTY_SINGLE_LINE_COMMENT){
                    HighLightUtil.setHighLigntSpanStr4Row(row);
                    if (row.getSpan_str() != null){
                        span = row.getSpan_str();
                        tv_content.append(span);
                    }
//                }
            }
//        SpannableString span_str = SpannableUtility.codeHighLight4JAVA(test);
//            Log.d("debug",span_builder.toString());
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
