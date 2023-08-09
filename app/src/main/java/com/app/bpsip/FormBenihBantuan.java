package com.app.bpsip;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.ResponseUpbs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormBenihBantuan extends AppCompatActivity {

    EditText edNama, edNik, edAlamat, edKelTani, edLuasLahan, edKomoditas, edBenih, edNoHp;
    ApiEndpoint apiEndpoint;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_benih_bantuan);



        edNama = findViewById(R.id.nama);
        edNik = findViewById(R.id.nik);
        edAlamat = findViewById(R.id.alamat);
        edKelTani = findViewById(R.id.tani);
        edLuasLahan = findViewById(R.id.lahan);
        edKomoditas = findViewById(R.id.komoditas);
        edBenih = findViewById(R.id.benih);
        edNoHp = findViewById(R.id.nohp);

        apiEndpoint = ApiCall.getApi().create(ApiEndpoint.class);

        btnSend = findViewById(R.id.kirim);
        btnSend.setOnClickListener(view -> {

            String nama = edNama.getText().toString();
            String nik = edNik.getText().toString();
            String alamat = edAlamat.getText().toString();
            String klmp_tani = edKelTani.getText().toString();
            String lahan = edLuasLahan.getText().toString();
            String komoditas = edKomoditas.getText().toString();
            String benih = edBenih.getText().toString();
            String noHp = edNoHp.getText().toString();

            if (nama.equals("")){
                Toast.makeText(FormBenihBantuan.this, "Nama Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (nik.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Nik Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (alamat.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Alamat Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (klmp_tani.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Nama Kelompok Tani Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (lahan.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Luas Kepemilikan Lahan Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (komoditas.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Komoditas Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (benih.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Kebutuhan Benih Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (noHp.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Nomor Hape Masih Kosong", Toast.LENGTH_SHORT).show();
            } else {
                Call<ResponseUpbs> postUpbsCall = apiEndpoint.postUpbs(nama, nik, alamat, klmp_tani, lahan, komoditas, benih, noHp,null);

                postUpbsCall.enqueue(new Callback<ResponseUpbs>() {
                    @Override
                    public void onResponse(Call<ResponseUpbs> call, Response<ResponseUpbs> response) {
                        Toast.makeText(getApplicationContext(),"Form Berhasil Di Kirim", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(FormBenihBantuan.this, Layanan.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onFailure(Call<ResponseUpbs> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());
                        Log.e(TAG, "onFailure: "+t.getCause());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        BottomNavigationView botNavbar = findViewById(R.id.navbar_benih);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(FormBenihBantuan.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(FormBenihBantuan.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(FormBenihBantuan.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(FormBenihBantuan.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(FormBenihBantuan.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}