package com.example.appbancaphe;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        TextView dkii = findViewById(R.id.dangkiii);
        Button dki = findViewById(R.id.btndki);
        TextInputEditText userdki = findViewById(R.id.tieNhapUsernamesignup);
        TextInputEditText mkdki = findViewById(R.id.tieNhapmatkhausignup);

        dkii.setOnClickListener(v -> {
            //startActivity(new Intent(Register.this, MainActivity.class));
            //khi activity đăng nhập chưa finish
            //nên chỉ cần finish activity đăng kỹ sẽ quay lại activity đăng nhập
            //cách này sẽ tối ưu hơn - huynk ph38086
            finish();
        });

        dki.setOnClickListener(v -> {
            String email = userdki.getText().toString();
            String password = mkdki.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                // Xử lý trường hợp email hoặc mật khẩu rỗng
                Toast.makeText(Register.this, "Vui lòng nhập đầy đủ thông tin",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, MainActivity.class));
                    finish();
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    // Kiểm tra nếu là lỗi định dạng email không đúng
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(Register.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}