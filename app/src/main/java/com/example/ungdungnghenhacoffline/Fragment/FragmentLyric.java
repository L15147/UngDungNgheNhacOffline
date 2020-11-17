package com.example.ungdungnghenhacoffline.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungnghenhacoffline.R;


public class FragmentLyric extends Fragment {



    public FragmentLyric() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lyric, container, false);
        TextView tvLyrics = view.findViewById(R.id.tvLyrics);
        tvLyrics.setText("Chưa có lời cho bài hát này");
        return view;
    }
}