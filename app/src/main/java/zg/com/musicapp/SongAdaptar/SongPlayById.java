package zg.com.musicapp.SongAdaptar;

/**
 * Created by ZG on 2018/5/13.
 */

public class SongPlayById {
    private String songinfo;
    private int error_code;
    private String bitrate;

    public class Songinfo{
        private String ting_uid;
        private String pic_premium;
        private String si_proxycompany;
        private String author;
        private String song_id;
        private String title;
        private String lrclink;
        private String pic_big;
        private String pic_small;
        private String album_title;

        public String getAlbum_title() {return album_title;}
        public void setAlbum_title(String album_title) {this.album_title = album_title;}

        public String getTing_uid() {return ting_uid;}
        public void setTing_uid(String ting_uid) {this.ting_uid = ting_uid;}

        public String getPic_premium() {return pic_premium;}
        public void setPic_premium(String pic_premium) {this.pic_premium = pic_premium;}

        public String getSi_proxycompany() {return si_proxycompany;}
        public void setSi_proxycompany(String si_proxycompany) {this.si_proxycompany = si_proxycompany;}

        public String getAuthor() {return author;}
        public void setAuthor(String author) {this.author = author;}

        public String getSong_id() {return song_id;}
        public void setSong_id(String song_id) {this.song_id = song_id;}

        public String getTitle() {return title;}
        public void setTitle(String title) {this.title = title;}

        public String getLrclink() {return lrclink;}
        public void setLrclink(String lrclink) {this.lrclink = lrclink;}

        public String getPic_big() {return pic_big;}
        public void setPic_big(String pic_big) {this.pic_big = pic_big;}

        public String getPic_small() {return pic_small;}
        public void setPic_small(String pic_small) {this.pic_small = pic_small;}
    }

    public class bitrate{
        private String file_extension;//音乐类型
        private String file_link;//音乐播放地址
        private int file_size ;//音乐大小

        public String getFile_extension() {return file_extension;}
        public void setFile_extension(String file_extension) {this.file_extension = file_extension;}

        public String getFile_link() {return file_link;}
        public void setFile_link(String file_link) {this.file_link = file_link;}

        public int getFile_size() {return file_size;}
        public void setFile_size(int file_size) {this.file_size = file_size;}
    }

    public String getSonginfo() {return songinfo;}
    public void setSonginfo(String songinfo) {this.songinfo = songinfo;}

    public int getError_code() {return error_code;}
    public void setError_code(int error_code) {this.error_code = error_code;}

    public String getBitrate() {return bitrate;}
    public void setBitrate(String bitrate) {this.bitrate = bitrate;}

}
