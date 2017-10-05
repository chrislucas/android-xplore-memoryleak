package xplore.br.com.xokirememoryleaks.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import xplore.br.com.xokirememoryleaks.thread.ImageDownloader;

/**
 * Created by r028367 on 05/10/2017.
 */

public class ImageDownloaderAsyncTask<P, T, R> extends AsyncTask<P, T, Bitmap> {

    @Override
    protected Bitmap doInBackground(Object[] objects) {
        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap bitmap = imageDownloader
                .downloadImage("https://medium.com/@ankit.sinhal/avoid-memory-leaks-reference-afd0e9dbb213");
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onCancelled(Bitmap o) {
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
