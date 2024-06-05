package com.example.appbancaphe.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appbancaphe.Login;
import com.example.appbancaphe.R;
import com.google.firebase.auth.FirebaseAuth;

public class Account extends Fragment {
    private FirebaseAuth mAuth;
    TextView txtinfo, txtrepass, txttdoanhthu, txtnhanvien, txtcoffe, txtloaicoffe, txtaddnv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        mAuth = FirebaseAuth.getInstance();

        // Đăng xuất khi người dùng nhấn nút đăng xuất
        view.findViewById(R.id.item_logout).setOnClickListener(v -> signOut());

        // Điều hướng
        txtinfo = view.findViewById(R.id.item_infor);
        txtrepass = view.findViewById(R.id.item_repass);
        txttdoanhthu = view.findViewById(R.id.item_revenue);
        txtnhanvien = view.findViewById(R.id.item_staffs);
        txtcoffe = view.findViewById(R.id.item_addcup);
        txtloaicoffe = view.findViewById(R.id.item_addtype);
        txtaddnv = view.findViewById(R.id.item_addstaff);

        txtinfo.setOnClickListener(v -> navigateToFragment(new Info()));
        txtrepass.setOnClickListener(v -> navigateToFragment(new RePass()));
        txttdoanhthu.setOnClickListener(v -> navigateToFragment(new Revenue()));
//        txtnhanvien.setOnClickListener(v -> navigateToFragment(new Staff()));
        txtcoffe.setOnClickListener(v -> navigateToFragment(new MoreCup()));
        txtloaicoffe.setOnClickListener(v -> navigateToFragment(new MoreCoffee()));
//        txtaddnv.setOnClickListener(v -> navigateToFragment(new AddSt()));

        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void signOut() {
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
        mAuth.signOut();
    }
}
