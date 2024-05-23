package com.example.appbancaphe;

import static android.app.ProgressDialog.show;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbancaphe.databinding.ActivityMainBinding;
import com.example.appbancaphe.fragment.Cart;
import com.example.appbancaphe.fragment.Home;
import com.example.appbancaphe.fragment.Product;
import com.example.appbancaphe.fragment.TaiKhoan;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.bottomnav.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.home:
                    relaceFragment(new Home());
                    break;
                case R.id.product:
                    relaceFragment(new Product());
                    break;
                case R.id.sanpham:
                    relaceFragment(new Cart());
                    break;
                case R.id.taikhoan:
                    relaceFragment(new TaiKhoan());
                    break;
            }
            return true;
        });




    }
    private void relaceFragment(TaiKhoan fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentlayout,fragment);
        fragmentTransaction.commit();
    }
    }
