package com.example.appbancaphe.fragment;

import static com.example.appbancaphe.model.NguoiDung.timKiemEmail;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbancaphe.R;
import com.example.appbancaphe.adapter.TrangChuAdapter;
import com.example.appbancaphe.model.Cafe;
import com.example.appbancaphe.model.NguoiDung;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    private int[] images;
    private ImageView imageView;
    private int currentIndex = 0;
    private Handler handler;
    private FirebaseAuth mAuth;

    private List<Cafe> cafes;
    private TrangChuAdapter adapter;
    private RecyclerView rv;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            NguoiDung nguoiDung = timKiemEmail(user.getEmail());
            TextView xinChao = view.findViewById(R.id.xin_chao);
            if (nguoiDung.ten_dang_nhap != null)
                xinChao.setText("Ngày mới tốt lành, " + nguoiDung.ten_dang_nhap + "!");
        }

        cafes = new ArrayList<>();
        duLieuMau();
        rv = view.findViewById(R.id.rv);
        adapter = new TrangChuAdapter(getActivity(), cafes);
        rv.setAdapter(adapter);

        new FCT();

        images = new int[]{R.drawable.default_banner, R.drawable.banner2, R.drawable.banner3};
        //không nên gán dữ liệu bên ngoài hàm

        imageView = view.findViewById(R.id.banner);
        handler = new Handler();
        startSlideshow();

        return view;
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
                URL url = new URL("...");//link database
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) responnse.append(line);
                reader.close();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
            return responnse.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.isEmpty()) {
                try {
                    JSONObject json = new JSONObject(s);
                    JSONArray array = json.getJSONArray("cafes");
                    for(int i = 0; i <= array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Cafe cafe = new Cafe();
                        cafe.id = object.getString("id");
                        cafe.anh = object.getString("anh");
                        cafe.loai = object.getString("loai");
                        cafe.kich_co = object.getInt("kich_co");
                        cafe.don_gia = object.getDouble("don_gia");
                        cafe.trang_thai = object.getInt("trang_thai");
                        //lưu ý: nhớ để ý viết đúng tên trường
                        cafes.add(cafe);
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(getActivity(), "Dữ liệu trống hoặc đọc dữ liệu thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    void duLieuMau() {
        cafes.add(new Cafe("cf01", "", "Cafe Nâm", 1, 30000, 1));
        cafes.add(new Cafe("cf02", "", "Cafe Đen", 3, 75000, 0));
        cafes.add(new Cafe("cf03", "", "Cafe muốn", 2, 35000, 1));
        cafes.add(new Cafe("cf04", "", "Cafe chồn", 1, 50000, 1));
    }
}
