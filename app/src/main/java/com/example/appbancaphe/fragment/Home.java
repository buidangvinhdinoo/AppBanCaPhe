package com.example.appbancaphe.fragment;

import static com.example.appbancaphe.model.NguoiDung.timKiemEmail;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbancaphe.R;
import com.example.appbancaphe.model.NguoiDung;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends Fragment {
    private int[] images = {R.drawable.default_banner, R.drawable.banner2, R.drawable.banner3};
    private ImageView imageView;
    private int currentIndex = 0;
    private Handler handler;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageView = view.findViewById(R.id.banner);
        handler = new Handler();
        startSlideshow();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            NguoiDung nguoiDung = timKiemEmail(user.getEmail());
            TextView xinChao = view.findViewById(R.id.xin_chao);
            if (nguoiDung.ten_dang_nhap != null)
                xinChao.setText("Ngày mới tốt lành, " + nguoiDung.ten_dang_nhap + "!");
        }

        return view;
    }

    private void startSlideshow() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentIndex == images.length) {
                    currentIndex = 0;
                }
                imageView.setImageResource(images[currentIndex]);
                currentIndex++;
                handler.postDelayed(this, 3000);
            }
        }, 3000);
    }
}
