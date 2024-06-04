package com.example.appbancaphe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbancaphe.fragment.Account;
import com.example.appbancaphe.fragment.Home;
import com.example.appbancaphe.fragment.Product;
import com.example.appbancaphe.fragment.Cart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    public static BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.pageTrangChu);
        loadFragment(new Home());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.pageTrangChu){
            Home frgHome = new Home();
            loadFragment(frgHome);
        } else if(item.getItemId() == R.id.pageSanPham){
            Product frgSp = new Product();
            loadFragment(frgSp);
        } else if(item.getItemId() == R.id.pageBanHang){
            Cart frgst = new Cart();
            loadFragment(frgst);
        } else if(item.getItemId() == R.id.pageTaiKhoan){
            Account frgacc = new Account();
            loadFragment(frgacc);
        }

        return true;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}