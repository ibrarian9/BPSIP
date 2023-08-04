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
import com.app.bpsip.Model.Upbs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegBenihBantuan extends AppCompatActivity {

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
            Call<Upbs> postUpbsCall = apiEndpoint.postUpbs();

            postUpbsCall.enqueue(new Callback<Upbs>() {
                @Override
                public void onResponse(Call<Upbs> call, Response<Upbs> response) {
                    Log.e(TAG, "onResponse: "+response.message());
                    Log.e(TAG, "onResponse: "+response.code());
                    Toast.makeText(getApplicationContext(),"Form Berhasil Di Kirim", Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(Call<Upbs> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage());
                    Log.e(TAG, "onFailure: "+t.getCause());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        });

        BottomNavigationView botNavbar = findViewById(R.id.navbar_benih);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(RegBenihBantuan.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(RegBenihBantuan.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(RegBenihBantuan.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(RegBenihBantuan.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(RegBenihBantuan.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}