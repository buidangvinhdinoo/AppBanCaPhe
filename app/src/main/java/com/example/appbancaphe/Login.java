package com.example.appbancaphe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        TextInputEditText userdnhap = findViewById(R.id.tieuNhapUsername);
        TextInputEditText mkdnhap = findViewById(R.id.tieNhapmatkhau);
        TextView btnsignup = findViewById(R.id.btnsignup);
        TextView quenmk = findViewById(R.id.forgot);
        Button signin = findViewById(R.id.btnDangnhap);

        signin.setOnClickListener(v -> {
            String email = userdnhap.getText().toString();
            String password = mkdnhap.getText().toString();

            // Tài khoản admin, để đăng nhập test nhanh
            if (email.equals("admin") && password.equals("admin")) {
                Toast.makeText(getApplicationContext(), "Chào admin!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Vui lòng nhập đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Đăng nhập thành công, lưu thông tin tài khoản vào SharedPreferences
                        FirebaseUser user = mAuth.getCurrentUser();
                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    } else {
                        // Đăng nhập thất bại
                        Log.w("Main", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(Login.this, "Vui lòng kiểm tra lại tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnsignup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });

        quenmk.setOnClickListener(v -> {
            String email = userdnhap.getText().toString();
            if (email.isEmpty()) {
                Toast.makeText(Login.this, "Vui lòng nhập email của bạn", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Vui lòng kiểm tra hộp thư email", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Lỗi gửi email", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
