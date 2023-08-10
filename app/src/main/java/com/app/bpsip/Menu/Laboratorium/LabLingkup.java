package com.app.bpsip.Menu.Laboratorium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Menu.Navbar.Agrostandar;
import com.app.bpsip.Menu.Navbar.Dashboard;
import com.app.bpsip.Menu.Navbar.Kontak;
import com.app.bpsip.Menu.Navbar.Layanan;
import com.app.bpsip.Menu.Navbar.Organisasi;
import com.app.bpsip.Model.ResponseLab;
import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabLingkup extends AppCompatActivity {

    TextView lingkup;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lingkup);

        lingkup = findViewById(R.id.tvLingkup);

        api = ApiCall.getApi().create(ApiEndpoint.class);

        api.getLab().enqueue(new Callback<ResponseLab>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLab> call, @NonNull Response<ResponseLab> response) {
                assert response.body() != null;
                String text = response.body().getHasil().getProfileLabRuangLingkup();
                lingkup.setText(text);
            }
            @Override
            public void onFailure(@NonNull Call<ResponseLab> call, @NonNull Throwable t) {

            }
        });

        BottomNavigationView botNavbar = findViewById(R.id.navbar_kontak);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}