package xplore.br.com.xokirememoryleaks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import xplore.br.com.xokirememoryleaks.thread.ImageDownloader;

public class XploreMemoryLeakAsyncTask extends AppCompatActivity {

    private class MyAsyncTask<P, T, R> extends AsyncTask<P, T, Bitmap> {
        @Override
        protected Bitmap doInBackground(P... ps) {
            ImageDownloader imageDownloader = new ImageDownloader();
            return imageDownloader.downloadImage("https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_memory_leak_async_task);
        new MyAsyncTask().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getCanonicalName(), "DESTROY");
    }
}
