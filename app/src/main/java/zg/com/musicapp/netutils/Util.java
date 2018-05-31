package zg.com.musicapp.netutils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZG on 2018/5/20.
 * 创建出封面图片
 */

public class Util {
    static public Bitmap createThumbFromUir(ContentResolver res, Uri albumUri) {
        InputStream in = null;
        Bitmap bmp = null;
        try {
            in = res.openInputStream(albumUri);
            BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();
            bmp = BitmapFactory.decodeStream(in, null, sBitmapOptions);
            in.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }
}
