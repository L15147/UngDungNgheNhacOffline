package com.example.ungdungnghenhacoffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ungdungnghenhacoffline.Adapter.ViewPagerAdapter;
import com.example.ungdungnghenhacoffline.Fragment.FragmentLyric;
import com.example.ungdungnghenhacoffline.Fragment.FragmentPlayMusic;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView tvTitle, tvTimeStart, tvTimeEnd;
    SeekBar seekBar;
    ImageButton imgButtonPre, imgButtonStop, imgButtonPlay, imgButtonFF, imgButtonReplay;
    ArrayList<Song> songArrayList;
    int i = 4;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tvTitle = findViewById(R.id.textViewTitle);
        tvTimeEnd = findViewById(R.id.tvTimeEnd);
        tvTimeStart = findViewById(R.id.tvTimeStart);
        seekBar = findViewById(R.id.seekBar);
        imgButtonFF = findViewById(R.id.imgButtonNext);
        imgButtonPre = findViewById(R.id.imgButtonPre);
        imgButtonStop = findViewById(R.id.imgButtonRandom);
        imgButtonPlay = findViewById(R.id.imgButtonPlay);
        imgButtonReplay = findViewById(R.id.imgButtonNext);
        this.addTab(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        addSong();
        mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, songArrayList.get(i).getFile());

        tvTitle.setText(songArrayList.get(i).getTenBaiHat() );
        imgButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_play);
                }
                else {
                    mediaPlayer.start();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });

    }
    private void addTab(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentPlayMusic());
        adapter.addFrag(new FragmentLyric() );
        viewPager.setAdapter(adapter);
    }
    private void addSong(){
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song("Anh không đòi quà", R.raw.anh_khong_doi_qua, "OnlyC - Karik"));
        songArrayList.add(new Song("Anh là sinh viên", R.raw.anh_la_sinh_vien, "Karik"));
        songArrayList.add(new Song("2 thế giới", R.raw.hai_the_gioi, "Wowy - Karik"));
        songArrayList.add(new Song("Khu tao sống", R.raw.khu_tao_song, "Wowy - Karik"));
        songArrayList.add(new Song("Làm việc nước", R.raw.lam_viec_nuoc, "Various artists"));
        songArrayList.add(new Song("Yêu 5", R.raw.yeu_nam, "Rhysmatic"));
        songArrayList.add(new Song("Hai triệu năm", R.raw.hai_trieu_nam, "Đen Vâu"));
    }
}