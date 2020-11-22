package com.example.ungdungnghenhacoffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ungdungnghenhacoffline.Adapter.ViewPagerAdapter;
import com.example.ungdungnghenhacoffline.Fragment.FragmentLyric;
import com.example.ungdungnghenhacoffline.Fragment.FragmentPlayMusic;
import com.example.ungdungnghenhacoffline.CreateNotification;
import com.example.ungdungnghenhacoffline.Service.OnClearFromRecentService;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import static com.example.ungdungnghenhacoffline.Song.mediaPlayer;

public class PlayMusicActivity extends AppCompatActivity implements Playable  {
    private static final String CHANNEL_ID = "My channel";
    private static final int NOTIFICATION_ID = 001;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView tvTitle, tvTimeStart, tvTimeEnd, tvTenCasy, tvOpenPlayList;
    SeekBar seekBar;
    ImageButton imgButtonPre, imgButtonPlay, imgButtonNext, imgButtonRepeat, imgButtonRandom, imgButtonPlayList;
    ArrayList<Song> songArrayList;
    Intent intent;
    int i;
    boolean random = false;
    boolean repeat = false;
    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        setTitle("Dream Music");
        anhXa();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChanel();
            registerReceiver(broadcastReceiver, new IntentFilter("SONGS_SONGS"));
            startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
        }
        this.addTab(viewPager);
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        i = bundle.getInt("stt");
        tabLayout.setupWithViewPager(viewPager);
        addSong();
        khoiTaoMediaPlayer();
        setTvTimeEnd();
        updateTimeSong();
        mediaPlayer.start();
        imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mediaPlayer.start();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                }
                setTvTimeEnd();
                updateTimeSong();
            }
        });
        imgButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == true) {
                    i += 0;
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else if (random == true) {
                    Random random = new Random();
                    int index = random.nextInt(songArrayList.size());
                    i = index;
                    if (i > songArrayList.size() - 1) {
                        i = 0;
                    }
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else if (repeat == false) {
                    i++;
                    if (i > songArrayList.size() - 1) {
                        i = 0;
                    }
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else if (random == false) {
                    i++;
                    if (i > songArrayList.size() - 1) {
                        i = 0;
                    }
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });
        imgButtonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == true) {
                    i -= 0;
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else if (random == true) {
                    Random random = new Random();
                    int index = random.nextInt(songArrayList.size());
                    i = index;
                    if (i < 0) {
                        i = songArrayList.size() - 1;
                    }
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else if (repeat == false) {
                    i--;
                    if (i < 0) {
                        i = songArrayList.size() - 1;
                    }
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else if (random == false) {
                    i--;
                    if (i < 0) {
                        i = songArrayList.size() - 1;
                    }
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    khoiTaoMediaPlayer();
                    mediaPlayer.start();
                    setTvTimeEnd();
                    updateTimeSong();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });
        imgButtonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (random == true) {
                        random = false;
                        imgButtonRepeat.setImageResource(R.drawable.repeat3);
                        imgButtonRandom.setImageResource(R.drawable.random4);
                    }
                    imgButtonRepeat.setImageResource(R.drawable.repeat3);
                    repeat = true;
                } else {
                    imgButtonRepeat.setImageResource(R.drawable.repeat2);
                    repeat = false;
                }
            }
        });
        imgButtonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (random == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgButtonRepeat.setImageResource(R.drawable.repeat2);
                        imgButtonRandom.setImageResource(R.drawable.random5);
                    }
                    imgButtonRandom.setImageResource(R.drawable.random5);
                    random = true;
                } else {
                    imgButtonRandom.setImageResource(R.drawable.random4);
                    random = false;
                }
            }
        });
        imgButtonPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMusicActivity.this, ListNhacActivity.class);
                startActivity(intent);
            }
        });
        tvOpenPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMusicActivity.this, ListNhacActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tvTitle = findViewById(R.id.textViewTitle);
        tvTimeEnd = findViewById(R.id.tvTimeEnd);
        tvTimeStart = findViewById(R.id.tvTimeStart);
        seekBar = findViewById(R.id.seekBar);
        imgButtonNext = findViewById(R.id.imgButtonNext);
        imgButtonPre = findViewById(R.id.imgButtonPre);
        imgButtonRandom = findViewById(R.id.imgButtonRandom);
        imgButtonPlay = findViewById(R.id.imgButtonPlay);
        imgButtonRepeat = findViewById(R.id.imgButtonRepeat);
        tvTenCasy = findViewById(R.id.tvCaSy);
        imgButtonPlayList = findViewById(R.id.imgButtonListNhac);
        tvOpenPlayList = findViewById(R.id.tvOpenPlayList);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getExtras().getString("actionname");
            switch (action) {
                case CreateNotification.ACTION_PREVIOUS:
                    onSongPrevious();
                    break;
                case CreateNotification.ACTION_PLAY:

                    onSongPlay();

                    break;
                case CreateNotification.ACTION_NEXT:
                    onSongNext();
                    break;
            }
        }
    };

    @Override
    public void onSongPrevious() {
        if (repeat == true) {
            i -= 0;
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        } else if (random == true) {
            Random random = new Random();
            int index = random.nextInt(songArrayList.size());
            i = index;
            if (i < 0) {
                i = songArrayList.size() - 1;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        } else if (repeat == false) {
            i--;
            if (i < 0) {
                i = songArrayList.size() - 1;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        } else if (random == false) {
            i--;
            if (i < 0) {
                i = songArrayList.size() - 1;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

    @Override
    public void onSongPlay() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mediaPlayer.start();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        }
        setTvTimeEnd();
        updateTimeSong();
    }


    @Override
    public void onSongNext() {
        if (repeat == true) {
            i += 0;
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        } else if (random == true) {
            Random random = new Random();
            int index = random.nextInt(songArrayList.size());
            i = index;
            if (i > songArrayList.size() - 1) {
                i = 0;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        } else if (repeat == false) {
            i++;
            if (i > songArrayList.size() - 1) {
                i = 0;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        } else if (random == false) {
            i++;
            if (i > songArrayList.size() - 1) {
                i = 0;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            khoiTaoMediaPlayer();
            mediaPlayer.start();
            setTvTimeEnd();
            updateTimeSong();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.cancelAll();
        }
        unregisterReceiver(broadcastReceiver);
    }

    private void addTab(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentPlayMusic());
        adapter.addFrag(new FragmentLyric());
        viewPager.setAdapter(adapter);
    }

    private void addSong() {
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song(R.drawable.background, "Anh không đòi quà", R.raw.anh_khong_doi_qua, "OnlyC ft Karik"));
        songArrayList.add(new Song(R.drawable.background, "Anh là sinh viên", R.raw.anh_la_sinh_vien, "Karik"));
        songArrayList.add(new Song(R.drawable.background, "Anh đếch cần gì nhiều ngoài em", R.raw.anhdechcanginhieungoaiem, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Bạc phận", R.raw.bacphan1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Bài này chill phết", R.raw.bai_nay_chill_phet, "Đen ft Min"));
        songArrayList.add(new Song(R.drawable.background, "7 tỷ người", R.raw.baytynguoi1, "Lynk Lee"));
        songArrayList.add(new Song(R.drawable.background, "Bigcityboi", R.raw.bigcityboi, "Binz"));
        songArrayList.add(new Song(R.drawable.background, "Buồn thì cứ khóc đi", R.raw.buonthicukhocdi1, "Lynk Lee"));
        songArrayList.add(new Song(R.drawable.background, "Cánh hồng phai", R.raw.canh_hong_phai1, "Trấn Thành"));
        songArrayList.add(new Song(R.drawable.background, "Có anh ở đây rồi", R.raw.coanhodayroi1, "Anh Quân Idol"));
        songArrayList.add(new Song(R.drawable.background, "Có chàng trai viết lên cây", R.raw.cochangtraivietlencay1, "Phan Mạnh Quỳnh"));
        songArrayList.add(new Song(R.drawable.background, "Có em chờ", R.raw.coemcho1, "Min"));
        songArrayList.add(new Song(R.drawable.background, "Cô gái vàng", R.raw.co_gai_vang, "HuyR"));
        songArrayList.add(new Song(R.drawable.background, "Đố em biết anh đang nghĩ gì", R.raw.doembietanhdangnghigi, "Đen ft Justatee"));
        songArrayList.add(new Song(R.drawable.background, "Em của ngày hôm qua", R.raw.em_cua_ngay_hom_qua, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Em gì ơi", R.raw.emgioi1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Hai thế giới", R.raw.hai_the_gioi, "Wowy ft Karik"));
        songArrayList.add(new Song(R.drawable.background, "2 triệu năm", R.raw.hai_trieu_nam, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Hãy trao cho anh", R.raw.hay_trao_cho_anh, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Hoa hải đường", R.raw.hoahaiduong1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Hồng nhan", R.raw.hongnhan1, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Kém duyên", R.raw.kemduyen1, "RUM"));
        songArrayList.add(new Song(R.drawable.background, "Không phải dạng vừa đâu", R.raw.khongphaidangvuadau1, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Khu tao sống", R.raw.khu_tao_song, "Wowy ft Karik"));
        songArrayList.add(new Song(R.drawable.background, "Làm việc nước", R.raw.lam_viec_nuoc, "Various artists"));
        songArrayList.add(new Song(R.drawable.background, "Lối nhỏ", R.raw.loinho1, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Một bước yêu vạn dặm đau", R.raw.motbuocyeuvandamdau1, "Mr.Siro"));
        songArrayList.add(new Song(R.drawable.background, "1 triệu like", R.raw.mottrieulike, "Đen"));
        songArrayList.add(new Song(R.drawable.background, "Nguyên team đi vào hết", R.raw.nguyenteamdivaohet, "Binz"));
        songArrayList.add(new Song(R.drawable.background, "Như ngày hôm qua", R.raw.nhungayhomqua1, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "Nơi này có anh", R.raw.noinaycoanh, "Sơn Tùng MTP"));
        songArrayList.add(new Song(R.drawable.background, "OK", R.raw.ok1, "Binz"));
        songArrayList.add(new Song(R.drawable.background, "Sóng gió", R.raw.songgio, "Jack"));
        songArrayList.add(new Song(R.drawable.background, "Thằng điên", R.raw.thangdien1, "Justatee"));
        songArrayList.add(new Song(R.drawable.background, "Thằng hầu", R.raw.thanghau1, "Nhật Phong"));
        songArrayList.add(new Song(R.drawable.background, "Tháng tư là lời nói dối của em", R.raw.thangtulaloinoidoicuaem1, "Hà Anh Tuấn"));
        songArrayList.add(new Song(R.drawable.background, "Thiên đàng", R.raw.thiendang1, "Wowy ft JoliPoli"));
        songArrayList.add(new Song(R.drawable.background, "Ừ có anh đây", R.raw.ucoanhday1, "Tino"));
        songArrayList.add(new Song(R.drawable.background, "Yêu 5", R.raw.yeu_nam, "Rhysmatic"));
    }

    public void khoiTaoMediaPlayer() {

        if (mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(this, songArrayList.get(i).getFile());
        else {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, songArrayList.get(i).getFile());
        }
        tvTitle.setText(songArrayList.get(i).getTenBaiHat());
        tvTenCasy.setText(songArrayList.get(i).getTenCaSy());
        CreateNotification.createNotification(PlayMusicActivity.this, songArrayList.get(i), R.drawable.play, i, songArrayList.size() - 1);

    }


    private void createChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID, "Dream Music", NotificationManager.IMPORTANCE_LOW);
            notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public void setTvTimeEnd() {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        tvTimeEnd.setText(format.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void updateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                tvTimeStart.setText(format.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (repeat == true) {
                            i += 0;
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                            }
                            khoiTaoMediaPlayer();
                            mediaPlayer.start();
                            setTvTimeEnd();
                            updateTimeSong();
                            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                        } else if (random == true) {
                            Random random = new Random();
                            int index = random.nextInt(songArrayList.size());
                            i = index;
                            if (i > songArrayList.size() - 1) {
                                i = 0;
                            }
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                            }
                            khoiTaoMediaPlayer();
                            mediaPlayer.start();
                            setTvTimeEnd();
                            updateTimeSong();
                            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                        } else if (repeat == false) {
                            i++;
                            if (i > songArrayList.size() - 1) {
                                i = 0;
                            }
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                            }
                            khoiTaoMediaPlayer();
                            mediaPlayer.start();
                            setTvTimeEnd();
                            updateTimeSong();
                            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                        } else if (random == false) {
                            i++;
                            if (i > songArrayList.size() - 1) {
                                i = 0;
                            }
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                            }
                            khoiTaoMediaPlayer();
                            mediaPlayer.start();
                            setTvTimeEnd();
                            updateTimeSong();
                            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                        }
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }
}