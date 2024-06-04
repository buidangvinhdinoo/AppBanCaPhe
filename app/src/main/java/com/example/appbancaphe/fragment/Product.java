package com.example.appbancaphe.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbancaphe.R;
import com.example.appbancaphe.adapter.ProductAdapter;
import com.example.appbancaphe.model.Cup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Product extends Fragment {
    private List<Cup> cups;
    private ProductAdapter adapter;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        cups = new ArrayList<>();
        duLieuMau();
        rv = view.findViewById(R.id.rv);
        adapter = new ProductAdapter(getActivity(), cups);
        rv.setAdapter(adapter);

        new FPT();

        return view;
    }

    private class FPT extends AsyncTask<Void,Void,String> {
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
                    JSONArray array = json.getJSONArray("cups");
                    for(int i = 0; i <= array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Cup cup = new Cup();
                        cup.id = object.getString("id");
                        cup.anh = object.getString("anh");
                        cup.loai_id = object.getString("loai");
                        cup.kich_co = object.getInt("kich_co");
                        cup.don_gia = object.getDouble("don_gia");
                        cup.trang_thai = object.getInt("trang_thai");
                        //lưu ý: nhớ để ý viết đúng tên trường
                        cups.add(cup);
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
        cups.add(new Cup("cf01", "", "Cup Nâm", 1, 30000, 1));
        cups.add(new Cup("cf02", "", "Cup Đen", 3, 75000, 0));
        cups.add(new Cup("cf03", "", "Cup muốn", 2, 35000, 1));
        cups.add(new Cup("cf04", "", "Cup chồn", 1, 50000, 1));
    }
}