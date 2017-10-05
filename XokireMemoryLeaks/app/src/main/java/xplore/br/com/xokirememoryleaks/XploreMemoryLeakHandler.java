package xplore.br.com.xokirememoryleaks;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import xplore.br.com.xokirememoryleaks.handler.MyHandler;

public class XploreMemoryLeakHandler extends AppCompatActivity {
    // Memory Leak
    private final Handler handler = new MyHandler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v("HANDLER", "message");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplorer_ml_handler);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.v("HANDLER", "postDelayed");
                Message message = new Message();
                handler.sendMessage(message);
            }
        }, 1000 * 60);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getCanonicalName(), "Destroy");
    }
}
