package zg.com.musicapp.Fragment;

import android.app.Activity;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import zg.com.musicapp.Activity.MainFragmentActivity;
import zg.com.musicapp.Activity.MusicListActivity;
import zg.com.musicapp.Adaptar.AudioUtils;
import zg.com.musicapp.Adaptar.MusicItem;
import zg.com.musicapp.Adaptar.MusicItemAdapter;
import zg.com.musicapp.Adaptar.MyNet;
import zg.com.musicapp.Adaptar.Observe;
import zg.com.musicapp.Adaptar.SongItemAdapter;
import zg.com.musicapp.SongAdaptar.LocalSong;
import zg.com.musicapp.SongAdaptar.Song;
import zg.com.musicapp.SongAdaptar.SongIdByNameJson;
import zg.com.musicapp.R;
import zg.com.musicapp.netutils.AppUrl;
import zg.com.musicapp.netutils.ParseData;

public class ShouyeFragment extends Fragment {
    private final static String TAG = "shouyeFragment";
    private Button bt_search;
    private Button bt_song;
    private Button bt_paihang;
    private Button bt_last;
    private Button bt_next;
    private Button bt_play_stop;
    private EditText edit_search;
    MediaPlayer mediaPlayer;
    private List<Song> mSongList = new ArrayList<Song>();
    private List<Song> mSongList1 = new ArrayList<Song>() ;
    private ListView mSongListView;
    private Song  msong;
    private int count=0;
    private boolean isPause=false;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initsong();
        initView();
        ((MainFragmentActivity)getActivity()).setmSongList(mSongList1);
        controlPlay();


    }
    public void controlPlay(){
        //对MediaPlayer对象添加事件监听，当播放完成时重新开始音乐播放

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play(++count);
            }
        });

        //对暂停、继续按钮添加事件监听器
        bt_play_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()&&!isPause){
                    mediaPlayer.pause();
                    isPause=true;
                }else{
                    mediaPlayer.start();
                    isPause=false;
                }
            }
        });
        bt_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()&&!isPause){
                    mediaPlayer.pause();
                    isPause=true;
                    play(--count);
                }else{
                    play(--count);
                    isPause=false;
                }
            }
        });
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()&&!isPause){
                    mediaPlayer.pause();
                    isPause=true;
                    play(++count);
                }else{
                    play(++count);
                    isPause=false;
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shouye, container, false);
    }
    private void initView() {
        bt_search = getActivity().findViewById(R.id.bt_search);
        bt_song = getActivity().findViewById(R.id.bt_song);
        bt_paihang = getActivity().findViewById(R.id.bt_paihang);
        bt_last = getActivity().findViewById(R.id.bt_last);
        bt_play_stop =getActivity().findViewById(R.id.bt_play_stop);
        bt_next = getActivity().findViewById(R.id.bt_next);
        mediaPlayer = new MediaPlayer();
        edit_search = getActivity().findViewById(R.id.et_search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSongList.clear();
                searchSong();
            }
        });
        bt_song.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mSongList.clear();
                initsong();
            }
        });
        bt_paihang.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mSongList.clear();

            }
        });
    }
    private AdapterView.OnItemClickListener mOnSongClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //添加播放音乐的代码
            count = position;
            play(position);


        }
    };
    public void play(int position){
        mSongList1.add(mSongList.get(position));//将播放的音乐信息放入最近播放列表
        String songmid = mSongList.get(position).getSongmid();
        Uri uri = Uri
                .parse("http://ws.stream.qqmusic.qq.com/C100"+songmid+".m4a?fromtag=0&guid=126548448");

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(getContext(), uri);
            //mediaPlayer = MediaPlayer.create(getContext(),uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initsong() {
        String url1 = "https://c.y.qq.com/v8/fcg-bin/fcg_v8_toplist_cp.fcg?g_tk=5381&uin=0&format=json&inCharset=utf-8&outCharset=utf-8&notice=0&platform=h5&needNewCode=1&tpl=3&page=detail&type=top&topid=27&_=1519963122923";
        MyNet.requestGet(getContext(),url1, new Observe() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);
                try {

                    JSONObject object = new JSONObject(response);
                    JSONArray jarray = ParseData.parseObjectArray(object,"songlist");//获取歌曲json数组
                    for(int i=0;i<jarray.length();i++) {
                        msong = new Song();
                        String data = jarray.getJSONObject(i).getString("data");
                        String songmid = ParseData.parseString(data, "songmid");
                        msong.setSongmid(songmid);
                        String songname = ParseData.parseString(data, "songname");
                        msong.setSongname(songname);
                        int albumid = ParseData.parseInt(data, "albumid");
                        msong.setAlbumid(albumid);
                        String albumname = ParseData.parseString(data, "albumname");
                        msong.setAlbumname(albumname);
                        JSONObject dataobj = new JSONObject(data);
                        JSONArray ArrSinger = ParseData.parseObjectArray(dataobj,"singer");
                        String name = ArrSinger.getJSONObject(0).getString("name");
                        msong.setSingername(name);
                        //设置歌曲专辑图片，input Stream出错
                      /*  URL url = null;
                        try {
                            url = new URL("http://imgcache.qq.com/music/photo/album_300/80/300_albumpic_4048580_0.jpg");
                            //InputStream inputStream = url.openStream();
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");
                            InputStream inputStream = con.getInputStream();
                            int len=0;
                            byte buf[]=new byte[1024];
                            ByteArrayOutputStream out=new ByteArrayOutputStream();
                            while((len=inputStream.read(buf))!=-1){
                                out.write(buf,0,len);  //把数据写入内存
                            }
                            out.close();  //关闭内存输出流
                            byte[] b =out.toByteArray();
                            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                            msong.setThumb(bitmap);
                            inputStream.close();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        mSongList.add(msong);
                        Log.d(TAG, "onSuccess: " + mSongList.get(i).getSongname());
                    }

                    mSongListView = getActivity().findViewById(R.id.song_list);
                    // Log.d(TAG, "onSuccess: " + mSongList.get(1).getSingername());
                    SongItemAdapter adapter = new SongItemAdapter(getContext(), R.layout.music_item, mSongList);
                    Log.d(TAG, "onSuccess: " + "成功1");
                    mSongListView.setAdapter(adapter);
                    mSongListView.setOnItemClickListener(mOnSongClickListener);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(VolleyError ve) {
                Log.d(TAG, "onError: "+ve.toString());
            }
        });


    }

    private void searchSong() {
        String songname = edit_search.getText().toString();
       // String url = AppUrl.getSearchUrlByName(songname);
        String url1 = "https://c.y.qq.com/soso/fcgi-bin/search_for_qq_cp?g_tk=5381&uin=0&format=json&inCharset=utf-8&outCharset=utf-8&notice=0&platform=h5&needNewCode=1&w="+songname+"&zhidaqu=1&catZhida=1&t=0&flag=1&ie=utf-8&sem=1&aggr=0&perpage=20&n=20&p=1&remoteplace=txt.mqq.all&_=1520833663464";
        MyNet.requestGet(getContext(),url1, new Observe() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);
                try {

                    //JSONObject object = new JSONObject(response);
                     String data = ParseData.parseString(response,"data");//获取歌单数组
                    String song = ParseData.parseString(data,"song");
                    //String list = ParseData.parseString(song,"list");
                    JSONObject songobject = new JSONObject(song);
                    JSONArray jarray = ParseData.parseObjectArray(songobject,"list");//获取歌曲json数组
                    for(int i=0;i<jarray.length();i++) {
                        msong = new Song();
                        String list = jarray.get(i).toString();
                        String songmid = jarray.getJSONObject(i).getString("songmid");
                        msong.setSongmid(songmid);
                        String songname = jarray.getJSONObject(i).getString("songname");
                        msong.setSongname(songname);
                        int albumid = jarray.getJSONObject(i).getInt("albumid");
                        msong.setAlbumid(albumid);
                        String albumname = jarray.getJSONObject(i).getString("albumname");
                        msong.setAlbumname(albumname);
                        JSONObject listobj = new JSONObject(list);
                        JSONArray ArrSinger = ParseData.parseObjectArray(listobj,"singer");
                        String name = ArrSinger.getJSONObject(0).getString("name");
                        msong.setSingername(name);
                        //设置歌曲专辑图片，input Stream出错
                      /*  URL url = null;
                        try {
                            url = new URL("http://imgcache.qq.com/music/photo/album_300/80/300_albumpic_4048580_0.jpg");
                            //InputStream inputStream = url.openStream();
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");
                            InputStream inputStream = con.getInputStream();
                            int len=0;
                            byte buf[]=new byte[1024];
                            ByteArrayOutputStream out=new ByteArrayOutputStream();
                            while((len=inputStream.read(buf))!=-1){
                                out.write(buf,0,len);  //把数据写入内存
                            }
                            out.close();  //关闭内存输出流
                            byte[] b =out.toByteArray();
                            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                            msong.setThumb(bitmap);
                            inputStream.close();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        mSongList.add(msong);
                        Log.d(TAG, "onSuccess: " + mSongList.get(i).getSongname());
                    }
                    mSongListView = getActivity().findViewById(R.id.song_list);
                    SongItemAdapter adapter = new SongItemAdapter(getContext(), R.layout.music_item, mSongList);
                    Log.d(TAG, "onSuccess: " + "成功1");
                    mSongListView.setAdapter(adapter);
                    mSongListView.setOnItemClickListener(mOnSongClickListener);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(VolleyError ve) {
                Log.d(TAG, "onError: "+ve.toString());
            }
        });
    }
}
