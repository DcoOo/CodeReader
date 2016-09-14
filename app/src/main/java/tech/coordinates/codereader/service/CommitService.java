package tech.coordinates.codereader.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CommitService extends Service {
    public CommitService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
