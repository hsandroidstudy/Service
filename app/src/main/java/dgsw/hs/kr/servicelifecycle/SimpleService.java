package dgsw.hs.kr.servicelifecycle;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class SimpleService extends Service {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        private void send(int result){
            Intent resultIntent = new Intent("com.example.ToActivity");
            resultIntent.putExtra("result", result);
            sendBroadcast(resultIntent);
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            int num1 = intent.getIntExtra("num1", 0);
            int num2 = intent.getIntExtra("num2", 0);
            String a = intent.getStringExtra("op");

            int sum = 0;

            switch (a){
                case "+" :
                    sum = num1 + num2;
                    break;
                case "-" :
                    sum = num1 + num2;
                    break;
            }
            send(sum);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SimpleService", "onDestroy()");
        unregisterReceiver(receiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null){
            Log.i("SimpleService", "onStartCommand() with null intent");
        }
        else {
            Log.i("SimpleService", "onStartCommand() with intent");
        }
        //return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("SimpleService", "onCreate()");
        IntentFilter filter = new IntentFilter("com.example.ToService");
        registerReceiver(receiver, filter);
    }

    public SimpleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
