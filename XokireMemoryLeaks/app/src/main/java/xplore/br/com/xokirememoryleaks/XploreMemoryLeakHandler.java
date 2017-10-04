package xplore.br.com.xokirememoryleaks;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import xplore.br.com.xokirememoryleaks.handler.MyHandler;

public class XploreMemoryLeakHandler extends AppCompatActivity {

    private final Handler handler = new MyHandler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("HANDLER", "message");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplorer_ml_handler);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("HANDLER", "postDelayed");
            }
        }, 1000 * 60 * 2);
    }
}
