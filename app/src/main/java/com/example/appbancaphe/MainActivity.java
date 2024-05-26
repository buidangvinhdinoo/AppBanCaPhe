package com.example.appbancaphe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbancaphe.databinding.ActivityMainBinding;
import com.example.appbancaphe.fragment.Cart;
import com.example.appbancaphe.fragment.Home;
import com.example.appbancaphe.fragment.Product;
import com.example.appbancaphe.fragment.TaiKhoan;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new Home());//mặc định

        binding.bottomnav.setOnItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();
            Fragment selectedFragment = null;

            if (itemId == R.id.btnhome) {
                selectedFragment = new Home();
            } else if (itemId == R.id.btnproduct) {
                selectedFragment = new Product();
            } else if (itemId == R.id.btncart) {
                selectedFragment = new Cart();
            } else if (itemId == R.id.btntaikhoan) {
                selectedFragment = new TaiKhoan();
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentlayout, fragment);
        fragmentTransaction.commit();
    }
}
