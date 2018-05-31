package zg.com.musicapp.Adaptar;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by ZG on 2018/5/20.
 */

public class MusicItem {
    String name; //存储音乐的名字
    String songUri; //存储音乐的Uri地址
    Uri albumUri;//存储音乐封面的Uri地址
    Bitmap thumb;//存储封面图片
    String singer;//存储音乐的播放时长，单位是毫秒

    public  MusicItem(String songUri, Uri albumUri, String strName, String singer) {
        this.name = strName;
        this.songUri = songUri;
        this.singer = singer;
        this.albumUri = albumUri;
    }
    public String getName(){return name;}
    public String getSongUri(){return songUri;}
    public String getSinger(){return singer;}

}
