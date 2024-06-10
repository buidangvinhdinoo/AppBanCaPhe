package com.example.appbancaphe.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbancaphe.DAOModel.DAOSanPham;
import com.example.appbancaphe.Model.TheLoai;
import com.example.appbancaphe.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ThemSPFrgm extends Fragment {
    private EditText  edAnh,edName, edPrice, edMoTa, btnAddSP, btnHuySP;
    AutoCompleteTextView edtLoaiSP;
    private DAOSanPham daoSanPham;
    final int REQUEST_CODE_GALLERY = 999;

    String strAnhSp,strTenSP, strGiaban, strLoaiSP, strMota;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_s_p_frgm, container, false);
        //ánh xạ
        daoSanPham = new DAOSanPham(getActivity());
        ImageView btnBackThemSP = view.findViewById(R.id.btnBackThemSP);
        edAnh = view.findViewById(R.id.edanhSP);
        edName = view.findViewById(R.id.edNameSP);
        edPrice = view.findViewById(R.id.edPrice);
        edMoTa = view.findViewById(R.id.edMoTa);
        edtLoaiSP = view.findViewById(R.id.edtLoaiSP);
        btnAddSP = view.findViewById(R.id.btnAcceptSP);
        btnHuySP = view.findViewById(R.id.btnHuySp);

        btnBackThemSP.setOnClickListener(v -> loadFragment(new Account_Fragment()));

//        Set Data cho spnLoaiSP - AnhNQ
        ArrayList<TheLoai> listTheLoai = daoSanPham.getDSLSP();
        ArrayList<String> listTenTL = new ArrayList<>();
        ArrayList<Integer> listMaTL = new ArrayList<>();
        int listTheLoaiSize = listTheLoai.size();
        if (listTheLoaiSize != 0){
            for (int i = 0; i < listTheLoaiSize; i++) {
                TheLoai theLoaiModel = listTheLoai.get(i);
                listTenTL.add(theLoaiModel.getTenLoai());
                listMaTL.add(theLoaiModel.getMaLoai());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getContext(), android.R.layout.select_dialog_item, listTenTL);

        edtLoaiSP.setThreshold(1);
        edtLoaiSP.setAdapter(adapter);


//        Set sự kiện Click Button Thêm
        btnAddSP.setOnClickListener(v -> {
            strAnhSp = edAnh.getText().toString();
            strTenSP = edName.getText().toString();
            strGiaban = edPrice.getText().toString();
            strMota = edMoTa.getText().toString();
            strLoaiSP = edtLoaiSP.getText().toString();
            boolean checkTL = false;

//                AutoComplete Text, Kiểm tra tồn tại loại sản phẩm.
            int index = 0;
            for (int i = 0; i < listTheLoaiSize; i++) {
                String mTenLoai = listTenTL.get(i);
                if (mTenLoai.equals(strLoaiSP)){
                    index = i;
                    checkTL = true;
                    break;
                }
            }

            int maLSP = 0;
            if (checkTL){
                maLSP = listMaTL.get(index);
            }

            if (checkEdt()) {
                if (checkTL){
                    daoSanPham.insertData(strAnhSp, strTenSP, Double.parseDouble(strGiaban), maLSP, strMota);
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    resetEdt();
                }
                else {
                    edtLoaiSP.setError("Loại sản phẩm không tồn tại!");
                    edtLoaiSP.setText("");
                }
            }
        });

//        Set sự kiện Click Button Hủy
        btnHuySP.setOnClickListener(view1 -> resetEdt());
        return view;
    }

    private byte[] imageToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return byteArray;
    }

    private void LayAnh() {
        //cấp quyền từ người dùng
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
            //cho phép sử dụng
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);//truy cập vào bộ nhớ của máy
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_GALLERY);
        }
    }

    //    Reset Edittext
    private void resetEdt() {
        edAnh.setText("");
        edAnh.setHintTextColor(Color.BLACK);
        edName.setText("");
        edName.setHintTextColor(Color.BLACK);
        edPrice.setText("");
        edPrice.setHintTextColor(Color.BLACK);
        edtLoaiSP.setText("");
        edtLoaiSP.setHintTextColor(Color.BLACK);
        edMoTa.setText("");
        edMoTa.setHintTextColor(Color.BLACK);
    }

    //    Check Form
    private boolean checkEdt() {

        boolean checkAdd = true;
        if (strTenSP.isEmpty()) {
            edName.setError("Vui lòng nhập!");
            edName.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (strGiaban.isEmpty()) {
            edPrice.setError("Vui lòng nhập!");
            edPrice.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (strLoaiSP.isEmpty()) {
            edtLoaiSP.setError("Vui lòng nhập!");
            edtLoaiSP.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (strMota.isEmpty()) {
            edMoTa.setError("Vui lòng nhập!");
            edPrice.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        return checkAdd;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}