package xplore.br.com.xokirememoryleaks.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by r028367 on 05/10/2017.
 */

public class ImageDownloader {
    public final Bitmap downloadImage(String path) {
        Bitmap bitmap = null;
        try {
            Thread.sleep(6000);
            URL url = new URL(path);
            InputStream in = url.openConnection().getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
            Log.i("BITMAP", bitmap.toString());
        }
        catch (Exception e) {
            String msg = e.getMessage() == null ? "S.O" : e.getMessage();
            Log.e("EXCEPTION_DOWNLOAD", msg);
        }
        return bitmap;
    }
}
