package zg.com.musicapp.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import zg.com.musicapp.Activity.MainFragmentActivity;
import zg.com.musicapp.Activity.MusicListActivity;
import zg.com.musicapp.Adaptar.AudioUtils;
import zg.com.musicapp.R;
import zg.com.musicapp.SongAdaptar.LocalSong;
import zg.com.musicapp.SongAdaptar.Song;


public class MyMusicFragment extends Fragment {
    private final static String TAG="Success:";
    Button bt_local;
    Button bt_recently;
    MediaPlayer medioPlayer;
    private List<Song> mSongList = new ArrayList<Song>() ;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSongList = ((MainFragmentActivity)getActivity()).getmSongList();
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mymusic, container, false);
    }

    private void initView() {
        bt_local = getActivity().findViewById(R.id.bt_local);
        bt_recently  = getActivity().findViewById(R.id.bt_recently);
        bt_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", "1");
                Intent intent =new Intent(getActivity(),MusicListActivity.class);
                intent.putExtras(bundle);
                               //启动
                startActivity(intent);
            }
        });
        bt_recently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", (Serializable) mSongList);
                bundle.putSerializable("id", "2");
                Intent intent =new Intent(getActivity(),MusicListActivity.class);
                intent.putExtras(bundle);
                //启动
                startActivity(intent);
            }
        });
    }
    /*
    private void getSong(){
        AudioUtils audioUtils = new AudioUtils();
        ArrayList<LocalSong> songs = audioUtils.getAllSongs(getContext());
        Log.d(TAG, "getSong: "+songs.get(1).getFileUrl());
        String fileurl = songs.get(1).getFileUrl();
        try {
            medioPlayer = new MediaPlayer();
            medioPlayer.setDataSource(fileurl);
            medioPlayer.prepare();
            medioPlayer.start();
        } catch (Exception e) {
        }
    }
*/
}
