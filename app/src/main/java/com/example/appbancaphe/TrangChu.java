package com.example.appbancaphe;

import static com.example.appbancaphe.model.NguoiDung.timKiemEmail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbancaphe.model.NguoiDung;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrangChu extends AppCompatActivity {
    private int[] images = {R.drawable.banner, R.drawable.banner2, R.drawable.banner3};
    private ImageView imageView;
    private int currentIndex = 0;
    private Handler handler;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        imageView = findViewById(R.id.imageView);
        handler = new Handler();
        startSlideshow();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();



        if(user != null) {
            NguoiDung nguoiDung = timKiemEmail(user.getEmail());
            TextView xin_chao = findViewById(R.id.xin_chao);
            if(nguoiDung.ten_dang_nhap != null)
                xin_chao.setText("Ngày mới tốt lành, " + nguoiDung.ten_dang_nhap + "!");
        }


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
                // Thay đổi ảnh sau mỗi 3 giây
                handler.postDelayed(this, 3000);
            }
        }, 3000);
    }
}