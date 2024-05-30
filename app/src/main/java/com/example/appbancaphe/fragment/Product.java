package com.example.appbancaphe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbancaphe.R;
import com.example.appbancaphe.adapter.ProductAdapter;
import com.example.appbancaphe.model.Cafe;

import java.util.ArrayList;
import java.util.List;

public class Product extends Fragment {
    private List<Cafe> cafes;
    private ProductAdapter adapter;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        cafes = new ArrayList<>();
        duLieuMau();
        rv = view.findViewById(R.id.rv);
        adapter = new ProductAdapter(getActivity(), cafes);
        rv.setAdapter(adapter);

        return view;
    }

    void duLieuMau() {
        cafes.add(new Cafe("cf01", "", "Cafe Nâm", 1, 30000, 1));
        cafes.add(new Cafe("cf02", "", "Cafe Đen", 3, 75000, 0));
        cafes.add(new Cafe("cf03", "", "Cafe muốn", 2, 35000, 1));
        cafes.add(new Cafe("cf04", "", "Cafe chồn", 1, 50000, 1));
    }
}