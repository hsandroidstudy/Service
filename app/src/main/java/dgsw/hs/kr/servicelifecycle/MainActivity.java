package dgsw.hs.kr.servicelifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void startService(View v){
        Intent intent = new Intent(this, SimpleService.class);
        startService(intent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                int result = intent.getIntExtra("result", Integer.MIN_VALUE);
                Toast.makeText(getApplicationContext(), "result = " + result, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void sendBroadcast(View v){
        Intent intent2 = new Intent("com.example.ToService");
        intent2.putExtra("num1", 1);
        intent2.putExtra("num2", 1);
        intent2.putExtra("op", "+");
        sendBroadcast(intent2);
    }

    public void stopService(View v){
        Intent intent = new Intent(this, SimpleService.class);
        stopService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter("com.example.ToActivity");
        registerReceiver(receiver, filter);
    }
}
