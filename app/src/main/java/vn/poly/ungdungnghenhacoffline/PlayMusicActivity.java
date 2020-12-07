package vn.poly.ungdungnghenhacoffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import vn.poly.ungdungnghenhacoffline.service.OnClearFromRecentService;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import static vn.poly.ungdungnghenhacoffline.Song.mediaPlayer;

public class PlayMusicActivity extends AppCompatActivity implements Playable  {

    ViewPager viewPager;
    TabLayout tabLayout;
    TextView tvTitle, tvTimeStart, tvTimeEnd, tvTenCasy, tvOpenPlayList;
    SeekBar seekBar;
    ImageButton imgButtonPre, imgButtonPlay, imgButtonNext, imgButtonRepeat, imgButtonRandom, imgButtonPlayList;
    ArrayList<Song> songArrayList;
    ImageView imageViewDisc;
    Intent intent;
    int i;
    boolean random = false;
    boolean repeat = false;
    NotificationManager notificationManager;
    ObjectAnimator animation;

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
        //this.addTab(viewPager);
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        i = bundle.getInt("stt");
        animation = ObjectAnimator.ofFloat(imageViewDisc, "rotation", 0, 360);
        animation.setDuration(10000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(ObjectAnimator.RESTART);
        animation.setInterpolator(new LinearInterpolator());

        //tabLayout.setupWithViewPager(viewPager);
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        animation.pause();
                    }

                } else {
                    mediaPlayer.start();
                    imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        animation.resume();
                    }
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
        imageViewDisc = findViewById(R.id.imgViewDisc);
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                animation.pause();
            }
        } else {
            mediaPlayer.start();
            imgButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                animation.resume();
            }
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



    private void addSong() {
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song(R.drawable.onlyc, "Anh không đòi quà", R.raw.anh_khong_doi_qua, "OnlyC ft Karik"));
        songArrayList.add(new Song(R.drawable.karik, "Anh là sinh viên", R.raw.anh_la_sinh_vien, "Karik"));
        songArrayList.add(new Song(R.drawable.denvau, "Anh đếch cần gì nhiều ngoài em", R.raw.anhdechcanginhieungoaiem, "Đen"));
        songArrayList.add(new Song(R.drawable.jack, "Bạc phận", R.raw.bacphan1, "Jack"));

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
        imageViewDisc.setImageResource(songArrayList.get(i).getImgSong());
        CreateNotification.createNotification(PlayMusicActivity.this, songArrayList.get(i), R.drawable.play, i, songArrayList.size() - 1);
        animation.start();
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