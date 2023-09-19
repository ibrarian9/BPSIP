package com.app.bpsip.Menu.Navbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;


import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.Hasil;
import com.app.bpsip.Model.ResponsePengunjung;
import com.app.bpsip.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {

    TextView tv1, tv2, pengunjung, number;
    int num;
    ApiEndpoint api;

    @Override
    protected void onStart(){
        super.onStart();

        api = ApiCall.getApi().create(ApiEndpoint.class);

        Call<ResponsePengunjung> get = api.getPengunjung();
        get.enqueue(new Callback<ResponsePengunjung>() {
            @Override
            public void onResponse(@NonNull Call<ResponsePengunjung> call, @NonNull Response<ResponsePengunjung> response) {
                assert response.body() != null;
                Hasil data = response.body().getHasil();
                data.setJumlahPengunjung(data.getJumlahPengunjung() + 1);
                Call<ResponsePengunjung> update = api.updatePengunjung(data);
                update.enqueue(new Callback<ResponsePengunjung>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponsePengunjung> call, @NonNull Response<ResponsePengunjung> response) {

                    }
                    @Override
                    public void onFailure(@NonNull Call<ResponsePengunjung> call, @NonNull Throwable t) {

                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call<ResponsePengunjung> call, @NonNull Throwable t) {

            }
        });
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView botNavbar = findViewById(R.id.navbar_dashboard);
        botNavbar.setSelectedItemId(R.id.menu_home);

        api = ApiCall.getApi().create(ApiEndpoint.class);

        Call<ResponsePengunjung> get = api.getPengunjung();
        get.enqueue(new Callback<ResponsePengunjung>() {
            @Override
            public void onResponse(@NonNull Call<ResponsePengunjung> call, @NonNull Response<ResponsePengunjung> response) {
                assert response.body() != null;
                num = response.body().getHasil().getJumlahPengunjung();
                Log.e("Hasil", String.valueOf(num));
                number = findViewById(R.id.tvNum);
                number.setText(String.valueOf(num));
            }
            @Override
            public void onFailure(@NonNull Call<ResponsePengunjung> call, @NonNull Throwable t) {

            }
        });

        pengunjung = findViewById(R.id.pengunjung);
        pengunjung.setText("Anda adalah Pengunjung ke ");

        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Dashboard.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Dashboard.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Dashboard.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Dashboard.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}