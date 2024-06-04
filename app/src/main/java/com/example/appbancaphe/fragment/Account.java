package com.example.appbancaphe.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbancaphe.MainActivity;
import com.example.appbancaphe.R;
import com.google.firebase.auth.FirebaseAuth;


public class Account extends Fragment {
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_account,container,false);

        mAuth = FirebaseAuth.getInstance();

        view.findViewById(R.id.item_infor).setOnClickListener(v -> loadFrag(new Info()));
        view.findViewById(R.id.item_repass).setOnClickListener(v -> loadFrag(new RePass()));
        view.findViewById(R.id.item_revenue).setOnClickListener(v -> loadFrag(new Revenue()));
        view.findViewById(R.id.item_staffs).setOnClickListener(v -> {});//
        view.findViewById(R.id.item_addcup).setOnClickListener(v -> loadFrag(new MoreCup()));
        view.findViewById(R.id.item_addtype).setOnClickListener(v -> loadFrag(new MoreCoffee()));
        view.findViewById(R.id.item_addstaff).setOnClickListener(v -> {});//
        // Đăng xuất khi người dùng nhấn nút đăng xuất
        view.findViewById(R.id.item_logout).setOnClickListener(v -> signOut());

        return view;
    }

    private void signOut() {
        mAuth.signOut();
        getActivity().finish();
    }

    private void loadFrag(Fragment frag) {
        MainActivity ma = (MainActivity) getActivity();
        ma.loadFragment(frag);
    }
}