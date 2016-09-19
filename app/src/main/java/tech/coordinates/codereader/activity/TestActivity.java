package tech.coordinates.codereader.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.TextView;

import tech.coordinates.codereader.service.TestService;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TestActivity extends AppCompatActivity {
    private ServiceConnection conn;
    private TestService service;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new LinearLayoutCompat(this));
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("Debug","Get Service");
                service = (TestService) ((TestService.TestBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("Debug","ServiceDisconnected");
            }

        };
        Intent i = new Intent(TestActivity.this,TestService.class);
        bindService(i,conn,Service.BIND_AUTO_CREATE);
        Log.d("Debug",service == null?"Service null test":"Service not null test");
    }
}
