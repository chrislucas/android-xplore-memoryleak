package xplore.br.com.xokirememoryleaks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;

import xplore.br.com.xokirememoryleaks.thread.ImageDownloader;

public class XploreMemoryLeakWorkerThread extends AppCompatActivity {

    private Thread worker = new Thread() {
        @Override
        public synchronized void start() {
            super.start();
            ImageDownloader imageDownloader = new ImageDownloader();
            imageDownloader.downloadImage("https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        }
    };

    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(6000);
                ImageDownloader imageDownloader = new ImageDownloader();
                imageDownloader.downloadImage("https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
            } catch (Exception e) {
                String msg = e.getMessage() == null ? "S.O" : e.getMessage();
                Log.e("EXCEPTION_THREAD_SLEEP", msg);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_memory_leak_worker_thread);
        // gerando
        new MyThread().start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getCanonicalName(), "DESTROY");
    }
}
