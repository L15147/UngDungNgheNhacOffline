package com.example.ungdungnghenhacoffline.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ungdungnghenhacoffline.R;


public class FragmentPlayMusic extends Fragment {



    public FragmentPlayMusic() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_music, container, false);
        ImageView imgViewDisc = view.findViewById(R.id.imgViewDisc);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
        animation.setRepeatCount(1000000);
        imgViewDisc.startAnimation(animation);
        return view;
    }
}