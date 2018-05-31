package zg.com.musicapp.Adaptar;

import java.util.List;

/**
 * Created by ZG on 2018/5/13.
 */

public class SongIdByNameJson {
    List<Song> song;
    private String error_code;
    private String  order;

    public List<Song> getList() {return song;}
    public void setList(List<Song> song) {this.song = song;}

    public String getError_code() {return error_code;}
    public void setError_code(String error_code) {this.error_code = error_code;}

    public String getOrder() {return order;}
    public void setOrder(String order) {this.order = order;}

    public class Song{
        private String weight;
        private String songname;
        private String songid;
        private String artistname;

        public String getWeight() {return weight;}
        public void setWeight(String weight) {this.weight = weight;}

        public String getSongname() {return songname;}
        public void setSongname(String songname) {this.songname = songname;}

        public String getSongid() {return songid;}
        public void setSongid(String singid) {this.songid = songid;}

        public String getArtistname() {return artistname;}
        public void setArtistname(String artistname) {this.artistname = artistname;}
    }
}
