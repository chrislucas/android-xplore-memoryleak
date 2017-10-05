package xplore.br.com.xokirememoryleaks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xplore.br.com.xokirememoryleaks.thread.ImageDownloader;

public class XploreMemoryLeakWorkerThread extends AppCompatActivity {
    // Solver memory leak
    // transformar esse atributo em estatico
    private static Thread worker = new Thread() {
        @Override
        public synchronized void start() {
            super.start();
            ImageDownloader imageDownloader = new ImageDownloader();
            imageDownloader.downloadImage("https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        }
    };

    // Classe que provavelmente gerará memory leak
    private class MyThreadMemoryLeak extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }
    private Thread thread1 = new MyThreadMemoryLeak();

    //
    private static MyThreadNonMemoryLeak thread2 = null;

    // Resolvendo o memory leak
    // inner class static nao tem sua referencia vinculada a Activity
    // assim a activity pode seguir seu ciclo de vida e a referencia
    // para a Thread nao vai vazar
    private static class MyThreadNonMemoryLeak extends Thread {
        boolean isRunning = true;
        @Override
        public void run() {
            super.run();
            clock(isRunning);
        }
        public void cancel() {
            isRunning = false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_memory_leak_worker_thread);
        if(thread2 == null) {
            thread2 = new MyThreadNonMemoryLeak();
        }
        thread2.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getCanonicalName(), "DESTROY");
        /*
        try {
            //thread1.join();
        } catch (InterruptedException e) {
            String message = e.getMessage() == null ? "" : e.getMessage();
            Log.e("EXCEPTION_THREAD_JOIS", message);
        }
        */
        thread2.cancel();
    }

    private static void clock(boolean isRunning) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm:ss", Locale.getDefault());
        while (isRunning) {
            try {
                Thread.sleep(1000);
                Date date = new Date(Calendar.getInstance().getTimeInMillis());
                Log.i("MY_WORK_THREAD", simpleDateFormat.format(date));
            } catch (Exception e) {
                String message = e.getMessage() == null ? "" : e.getMessage();
                Log.e("EXCEPTION_THREAD_SLEEP", message);
            }
        }
    }
}
