package tech.coordinates.codereader.activity;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.fragment.TextFragment;
import tech.coordinates.codereader.view.DirectoryTextView;

public class MainActivity extends AppCompatActivity implements TextFragment.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DirectoryTextView dirTV = (DirectoryTextView) findViewById(R.id.file_test);
//        dirTV.isOpened = false;
//        dirTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //目录被打开
//                if (!((DirectoryTextView)view).isOpened){
//                    ((DirectoryTextView) view).isOpened = true;
//                    ((DirectoryTextView) view).setPaint_color(Color.BLACK);
//                    view.invalidate();
//                    //列出当前目录下的所有子文件
//                    Log.d("Debug","Directory open");
//                }else {
//                    ((DirectoryTextView) view).isOpened = false;
//                    ((DirectoryTextView) view).setPaint_color(Color.parseColor("#FF34B3"));
//                    view.invalidate();
//                    Log.d("Debug","Directory close");
//                }
//            }
//        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
