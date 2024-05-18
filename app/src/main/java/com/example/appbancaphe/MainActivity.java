package com.example.appbancaphe;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        TextInputEditText userdnhap = findViewById(R.id.tieNhapUsername);
        TextInputEditText mkdnhap = findViewById(R.id.tieNhapmatkhau);

        TextView btnsignup = findViewById(R.id.btnsignup);
        TextView quenmk = findViewById(R.id.forgot);
        Button signin= findViewById(R.id.btnDangnhap);

        signin.setOnClickListener(v -> {
            String email = userdnhap.getText().toString();
            String password = mkdnhap.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin đăng nhập",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Main", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công",
                                Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this, TrangChu.class));
                        finish();

                        //updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Main", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại tài khoản hoặc mậy khẩu",
                                Toast.LENGTH_SHORT).show();

                        //updateUI(null);
                    }
                });
            }
        });

        btnsignup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Dki.class);
            startActivity(intent);
            finish();
        });
        
        quenmk.setOnClickListener(v -> {
            String email = userdnhap.getText().toString();
            if (email.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập email của bạn",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Vui lòng kiểm tra hộp thư email",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Lỗi gửi email", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    }
