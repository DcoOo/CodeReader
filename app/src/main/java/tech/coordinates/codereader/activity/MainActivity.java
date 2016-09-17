package tech.coordinates.codereader.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.fragment.TextFragment;
import tech.coordinates.codereader.view.DirectoryTextView;

public class MainActivity extends AppCompatActivity implements TextFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_item_setting:
                //点击设置时，切换到SettingActivity
                Intent i = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
