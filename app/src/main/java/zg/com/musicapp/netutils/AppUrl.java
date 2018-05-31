package zg.com.musicapp.netutils;

/**
 * Created by ZG on 2018/5/13.
 */

public class AppUrl {
    private static String baseurl = "http://tingapi.ting.baidu.com/v1/restserver/ting?" +
            "format=json或xml&calback=&from=webapp_music&";

    //获取列表type = 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,
    // 22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
    public static String getBillListUrlByName( int type){
        String url = baseurl +"method=baidu.ting.billboard.billList&type="+type+"&size=10&offset=0 ";
        return url;
    }
    //通过搜索歌名获取歌曲id地址
    public static String getSearchUrlByName(String songname){
        String url = baseurl +"method=baidu.ting.search.catalogSug&query="+songname;
        return url;
    }
    //通过歌曲id获取歌曲播放地址
    public static String getPlayUrlById(String songid){
        String url = baseurl+"method=baidu.ting.song.play&songid="+songid;
        return url;
    }
    //通过歌曲id获取LRC歌词地址
    public static String getLrcUrlById(String songid){
        String url = baseurl+"method=baidu.ting.song.lry&songid="+songid;
        return url;
    }
    //通过歌曲id获取下载地址
    public static String getDownloadUrlById(String songid){
        String url = baseurl+"method=baidu.ting.song.downWeb&songid="+
                songid+"&bit=24&_t=1393123213";
        return url;
    }
    //通过歌手id获取歌手信息
    public static String getTinginfoUrlById(String tinguid){
        String url = baseurl+"method=baidu.ting.artist.getInfo&tinguid="+tinguid;
        return url;
    }
    //通过歌曲id获取歌手歌曲列表
    public static String getSongListUrlById(String tinguid){
        String url = baseurl+"method=baidu.ting.artist.getInfo&tinguid="+tinguid+
                "&limits=6&use_cluster=1&order=2";
        return url;
    }
}
