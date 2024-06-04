package com.example.appbancaphe.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appbancaphe.Login;
import com.example.appbancaphe.R;
import com.google.firebase.auth.FirebaseAuth;


public class Account extends Fragment {
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_account,container,false);

        mAuth = FirebaseAuth.getInstance();

        // Đăng xuất khi người dùng nhấn nút đăng xuất
        view.findViewById(R.id.item_logout).setOnClickListener(v -> signOut());
        return view;
    }
    private void signOut() {
        mAuth.signOut();
        getActivity().finish();
    }
}