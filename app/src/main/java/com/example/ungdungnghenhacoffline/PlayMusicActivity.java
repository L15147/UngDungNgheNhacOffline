package com.example.ungdungnghenhacoffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.ungdungnghenhacoffline.Adapter.ViewPagerAdapter;
import com.example.ungdungnghenhacoffline.Fragment.FragmentLyric;
import com.example.ungdungnghenhacoffline.Fragment.FragmentPlayMusic;
import com.google.android.material.tabs.TabLayout;

public class PlayMusicActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        this.addTab(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void addTab(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentPlayMusic());
        adapter.addFrag(new FragmentLyric() );
        viewPager.setAdapter(adapter);
    }
}