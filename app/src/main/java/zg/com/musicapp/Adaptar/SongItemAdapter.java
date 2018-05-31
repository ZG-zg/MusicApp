package zg.com.musicapp.Adaptar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import zg.com.musicapp.R;
import zg.com.musicapp.SongAdaptar.Song;

/**
 * Created by ZG on 2018/5/20.
 */

public class SongItemAdapter extends BaseAdapter {

    private List<Song> mData;
    private final LayoutInflater mInflater;
    private final int mResource;
    private Context mContext;

    public SongItemAdapter(Context context, int resId, List<Song> data)
    {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);
        mResource = resId;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position): null ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
        }

        Song item = mData.get(position);

        TextView title =  convertView.findViewById(R.id.music_title);
        title.setText(item.getSongname());
        TextView singer =  convertView.findViewById(R.id.music_singer);
        singer.setText(item.getSingername());

        ImageView thumb =  convertView.findViewById(R.id.music_thumb);
        if(thumb != null) {
            if (item.getThumb() != null) {
                thumb.setImageBitmap(item.getThumb());
            } else {
                thumb.setImageResource(R.drawable.tooopen_18895187);
            }
        }

        return convertView;
    }

}
