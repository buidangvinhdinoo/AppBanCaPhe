package com.example.appbancaphe;

import static com.example.appbancaphe.model.NguoiDung.timKiemEmail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appbancaphe.model.NguoiDung;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrangChu extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            NguoiDung nguoiDung = timKiemEmail(user.getEmail());
            TextView xin_chao = findViewById(R.id.xin_chao);
            if(nguoiDung.ten_dang_nhap != null)
                xin_chao.setText("Ngày mới tốt lành, " + nguoiDung.ten_dang_nhap + "!");
        }
    }
}