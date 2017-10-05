package xplore.br.com.xokirememoryleaks;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import xplore.br.com.xokirememoryleaks.handler.MyHandler;

public class XploreMemoryLeakHandler extends AppCompatActivity {
    // Memory Leak
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v("HANDLER", "message");
        }
    };

    // Solver Memory Leak
    private Handler handler2;
    private Runnable runnable = new MyRunnable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplorer_ml_handler);
        handler2    = new MyHandler(this);
        handler2.postDelayed(runnable, 1000 * 60);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getCanonicalName(), "Destroy");
        handler2.removeCallbacks(runnable);
    }

    private static class MyHandler extends Handler {
        private Context context;
        public MyHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v("HANDLER", "message");
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            Log.v("RUNNABLE", "message");
        }
    }
}
