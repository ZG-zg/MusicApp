package zg.com.musicapp.SongAdaptar;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by ZG on 2018/5/26.
 */
public class Song implements Serializable {
    private String songname;
    private String songid;
    private String songmid;
    private int file_size ;//音乐大小
    private String singername;
    private String albumname;
    private int albumid;
    private Bitmap thumb;//存储封面图片

    public String getSongname() {return songname;}
    public void setSongname(String songname) {	this.songname = songname;}

    public String getSongid() {return songid;}
    public void setSongid(String songid) {this.songid = songid;}

    public int getFile_size() {return file_size;}
    public void setFile_size(int file_size) {this.file_size = file_size;}



    public String getAlbumname() {return albumname;}
    public void setAlbumname(String albumname) {this.albumname = albumname;}

    public String getSingername() {return singername;}
    public void setSingername(String singername) {this.singername = singername;}

    public int getAlbumid() {
        return albumid;
    }
    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }
    public String getSongmid() {
        return songmid;
    }
    public void setSongmid(String songmid) {
        this.songmid = songmid;
    }


    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }
}

