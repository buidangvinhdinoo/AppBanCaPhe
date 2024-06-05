package com.example.appbancaphe.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appbancaphe.R;

public class MoreCoffee extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  =inflater.inflate(R.layout.fragment_more_coffee, container, false);
        Button backButton = view.findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> {
            // Pop the back stack
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });

        return view;

    }
}