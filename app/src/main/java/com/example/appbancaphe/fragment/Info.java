package com.example.appbancaphe.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appbancaphe.R;

public class Info extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_info, container, false);
        Button backButton = view.findViewById(R.id.button_backinfo);
        backButton.setOnClickListener(v -> {
            // Pop the back stack
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }
}