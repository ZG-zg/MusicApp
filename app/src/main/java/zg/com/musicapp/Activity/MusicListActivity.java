package zg.com.musicapp.Activity;

import android.content.ContentUris;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zg.com.musicapp.Adaptar.AudioUtils;
import zg.com.musicapp.Adaptar.MusicItem;
import zg.com.musicapp.Adaptar.MusicItemAdapter;
import zg.com.musicapp.Adaptar.SongItemAdapter;
import zg.com.musicapp.Fragment.MyMusicFragment;
import zg.com.musicapp.R;
import zg.com.musicapp.SongAdaptar.LocalSong;
import zg.com.musicapp.SongAdaptar.Song;

public class MusicListActivity extends AppCompatActivity {
    private final static String TAG="Success:";
    Button bt_back;
    Button bt_last;
    Button bt_next;
    Button bt_play_stop;
    TextView text_titile;
    MediaPlayer medioPlayer = new MediaPlayer() ;
    private boolean isPause=false;
    private List<MusicItem> mMusicList = new ArrayList<MusicItem>();
    private ListView mMusicListView;
    private static List<Song> resultList = null;
    private String i;
    private int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        i = getIntent().getStringExtra("id");
        //当i=1是本地音乐
        if(i.equals("1")) {
            initmusic();
            mMusicListView = (ListView) findViewById(R.id.music_list);
            MusicItemAdapter adapter = new MusicItemAdapter(this, R.layout.music_item, mMusicList);
            mMusicListView.setAdapter(adapter);
        }
        //当i=2是最近播放
        else if(i.equals("2")){
            resultList = (List<Song>) getIntent().getSerializableExtra("song");
            mMusicListView = (ListView) findViewById(R.id.music_list);
            SongItemAdapter adapter = new SongItemAdapter(this, R.layout.music_item, resultList);
            mMusicListView.setAdapter(adapter);
        }
        initView();//初始化控件
        mMusicListView.setOnItemClickListener(mOnMusicItemClickListener);
        controlPlay();//控制播放


    }
    public void controlPlay(){
        //对MediaPlayer对象添加事件监听，当播放完成时开始下一首音乐播放
        medioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play(++count);
            }
        });

        //对暂停、继续按钮添加事件监听器
        bt_play_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medioPlayer.isPlaying()&&!isPause){
                    medioPlayer.pause();
                    bt_play_stop.setBackgroundResource(R.drawable.pause);
                    isPause=true;
                }else{
                    medioPlayer.start();
                    bt_play_stop.setBackgroundResource(R.drawable.play);
                    isPause=false;
                }
            }
        });
        //设置点击上一首的按钮
        bt_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medioPlayer.isPlaying()&&!isPause){
                    medioPlayer.pause();
                    isPause=true;
                    play(--count);
                }else{
                    play(--count);
                    isPause=false;
                }
                bt_play_stop.setBackgroundResource(R.drawable.play);
            }
        });
        //设置点击下一首的按钮
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medioPlayer.isPlaying()&&!isPause){
                    medioPlayer.pause();
                    isPause=true;
                    play(++count);
                }else{
                    play(++count);
                    isPause=false;
                }
                bt_play_stop.setBackgroundResource(R.drawable.play);
            }
        });

    }
    //播放列表里点击到的音乐
    private AdapterView.OnItemClickListener mOnMusicItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //添加播放音乐的代码

            count = position;
            play(position);

        }
    };

    public void play(int position){
        String item="";
        if(i.equals("1")) {
            if(position>=mMusicList.size()){
                position = 0;
            }
            else if(position<0){
                position =mMusicList.size()-1;
            }

            item = mMusicList.get(position).getSongUri();
        }
        else if(i.equals("2")) {
            if(position>=resultList.size()){
                position = 0;
            }
            else if(position<0){
                position =resultList.size()-1;
            }
            String songmid = resultList.get(position).getSongmid();
            item = "http://ws.stream.qqmusic.qq.com/C100"+songmid+".m4a?fromtag=0&guid=126548448";
        }
        // String item = mMusicList.get(position).getSongUri();
        try {
            //medioPlayer = new MediaPlayer();
            medioPlayer.reset();
            medioPlayer.setDataSource(item);
            medioPlayer.prepare();
            medioPlayer.start();
            bt_play_stop.setBackgroundResource(R.drawable.play);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initView() {
        bt_back = (Button) findViewById(R.id.bt_back);
        bt_last = (Button) findViewById(R.id.bt_last);
        bt_play_stop =(Button) findViewById(R.id.bt_play_stop);
        bt_next = (Button) findViewById(R.id.bt_next);
        text_titile = (TextView) findViewById(R.id.text_title);
        setTitile();
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MusicListActivity.this,MyMusicFragment.class);
                //启动
                startActivity(intent);
            }
        });
    }

    private void setTitile() {
        if(i.equals("1")) {
            text_titile.setText("本地音乐");
        }
        else if(i.equals("2")) {
            text_titile.setText("最近播放");
        }
    }


    private void initmusic() {

        AudioUtils audioUtils = new AudioUtils();
        ArrayList<LocalSong> songs = audioUtils.getAllSongs(MusicListActivity.this);
        for(int i=0;i<songs.size();i++){
            int albumId =songs.get(i).getAlbumid();
            Uri albumUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
            MusicItem musicitem = new MusicItem(songs.get(i).getFileUrl(),albumUri,songs.get(i).getTitle(),songs.get(i).getSinger());
            mMusicList.add(musicitem);
        }



    }

}
