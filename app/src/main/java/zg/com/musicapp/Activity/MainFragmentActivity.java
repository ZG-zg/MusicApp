package zg.com.musicapp.Activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import zg.com.musicapp.Fragment.Fragment3;
import zg.com.musicapp.Fragment.MyMusicFragment;
import zg.com.musicapp.Fragment.ShouyeFragment;
import zg.com.musicapp.Fragment.UserFragment;
import zg.com.musicapp.R;
import butterknife.Bind;
import zg.com.musicapp.SongAdaptar.Song;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TabHost;

import java.util.List;

public class MainFragmentActivity extends FragmentActivity {
    @Bind(android.R.id.tabcontent)
    FrameLayout fl_shouye;
    @Bind(android.R.id.tabs)
    RadioGroup tabs;
    @Bind(android.R.id.tabhost)
    FragmentTabHost ft;
    @Bind(R.id.bt_shouye)
    Button bt_shouye;
    @Bind(R.id.bt_my)
    Button bt_my;
    @Bind(R.id.bt_3)
    Button bt_3;
    @Bind(R.id.bt_user)
    Button bt_user;
    private static String name ;
    private static List<Song> mSongList ;

    public static List<Song> getmSongList() {
        return mSongList;
    }

    public static void setmSongList(List<Song> mSongList) {
        MainFragmentActivity.mSongList = mSongList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        MainFragmentActivity.name = name;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        ButterKnife.bind(this);
        name = getIntent().getStringExtra("username");
        initview();
    }

    private void initview() {
        //设置页面的显示
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        ft.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        //设置我们的指示器
        TabHost.TabSpec ts1 = ft.newTabSpec("0").setIndicator("");
        TabHost.TabSpec ts2 = ft.newTabSpec("1").setIndicator("");
        TabHost.TabSpec ts3 = ft.newTabSpec("2").setIndicator("");
        TabHost.TabSpec ts4 = ft.newTabSpec("3").setIndicator("");
        //加入刚才定义的选项卡
        ft.addTab(ts1, ShouyeFragment.class,null);
        ft.addTab(ts2, MyMusicFragment.class,null);
        ft.addTab(ts3, Fragment3.class,null);
        ft.addTab(ts4, UserFragment.class,null);
    }
    @OnClick({R.id.bt_shouye,R.id.bt_my,R.id.bt_3,R.id.bt_user})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.bt_shouye:
                ft.setCurrentTab(0);
                break;
            case R.id.bt_my:
                ft.setCurrentTab(1);
                break;
            case R.id.bt_3:
                ft.setCurrentTab(2);
                break;
            case R.id.bt_user:
                ft.setCurrentTab(3);
                break;
        }
    }



}
