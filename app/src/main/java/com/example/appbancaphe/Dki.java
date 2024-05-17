package com.example.appbancaphe;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class Dki extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dki);
        mAuth = FirebaseAuth.getInstance();
        TextView dkii = findViewById(R.id.dangkiii);
        Button dki = findViewById(R.id.btndki);
        TextInputEditText userdki = findViewById(R.id.tieNhapUsernamesignup);
        TextInputEditText mkdki = findViewById(R.id.tieNhapmatkhausignup);
        dkii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dki.this, MainActivity.class));
                finish();
            }
        });
        dki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = userdki.getText().toString();
                String password = mkdki.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    // Xử lý trường hợp email hoặc mật khẩu rỗng
                    Toast.makeText(Dki.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Dki.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Dki.this, MainActivity.class));
                            finish();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            // Kiểm tra nếu là lỗi định dạng email không đúng
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(Dki.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Dki.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });


    }
}