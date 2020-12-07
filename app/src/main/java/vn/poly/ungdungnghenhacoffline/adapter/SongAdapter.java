package vn.poly.ungdungnghenhacoffline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import vn.poly.ungdungnghenhacoffline.R;
import vn.poly.ungdungnghenhacoffline.Song;

import java.util.List;

public class SongAdapter extends BaseAdapter {
    List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song, parent, false);
        ImageView imgViewSong = convertView.findViewById(R.id.imgViewSong);
        TextView tvTenBaiHat = convertView.findViewById(R.id.tvTenBaiHat);
        TextView tvTenCaSy = convertView.findViewById(R.id.tvTenCaSy);
        Song song = songList.get(position);
        imgViewSong.setImageResource(song.getImgSong());
        tvTenBaiHat.setText(song.getTenBaiHat());
        tvTenCaSy.setText(song.getTenCaSy());
        return convertView;
    }

}
