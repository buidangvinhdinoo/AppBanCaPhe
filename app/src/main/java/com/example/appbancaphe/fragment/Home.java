package com.example.appbancaphe.fragment;

import static com.example.appbancaphe.model.NguoiDung.timKiemEmail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbancaphe.R;
import com.example.appbancaphe.adapter.TrangChuAdapter;
import com.example.appbancaphe.model.Cafe;
import com.example.appbancaphe.model.NguoiDung;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URL;
import java.util.List;

public class Home extends Fragment {
    private int[] images = {R.drawable.default_banner, R.drawable.banner2, R.drawable.banner3};
    private ImageView imageView;
    private int currentIndex = 0;
    private Handler handler;
    private FirebaseAuth mAuth;

    private List<Cafe> cafes;
    private TrangChuAdapter adapter;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageView = view.findViewById(R.id.banner);
        handler = new Handler();
        startSlideshow();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            NguoiDung nguoiDung = timKiemEmail(user.getEmail());
            TextView xinChao = view.findViewById(R.id.xin_chao);
            if (nguoiDung.ten_dang_nhap != null)
                xinChao.setText("Ngày mới tốt lành, " + nguoiDung.ten_dang_nhap + "!");
        }

        lv = view.findViewById(R.id.lv);
        updateData();

        new FCT();

        return view;
    }

    private void updateData() {
        adapter = new TrangChuAdapter(getContext(), cafes);
        lv.setAdapter(adapter);
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
                handler.postDelayed(this, 3000);
            }
        }, 3000);
    }

    private class FCT extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder responnse = new StringBuilder();
            try {
                URL url = new URL("...");// can link database
                //HTTP
            } catch (Exception e){
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
