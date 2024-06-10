package com.example.appbancaphe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbancaphe.FragmentManager.Account_Fragment;
import com.example.appbancaphe.FragmentManager.HomeFrgm;
import com.example.appbancaphe.FragmentManager.ProductFrgm;
import com.example.appbancaphe.FragmentManager.StoreFrgm;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    public static BottomNavigationView bottomNavigationView;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.pageTrangChu);
        loadFragment(new HomeFrgm());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.pageTrangChu) {
            HomeFrgm frgHome = new HomeFrgm();
            loadFragment(frgHome);
        } else if (item.getItemId() == R.id.pageSanPham) {
            ProductFrgm frgSp = new ProductFrgm();
            loadFragment(frgSp);
        } else if (item.getItemId() == R.id.pageBanHang) {
            StoreFrgm frgst = new StoreFrgm();
            loadFragment(frgst);

        } else if (item.getItemId() == R.id.pageTaiKhoan) {
            Account_Fragment frgacc = new Account_Fragment();
            loadFragment(frgacc);
        }
        return true;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.commit();
    }
}