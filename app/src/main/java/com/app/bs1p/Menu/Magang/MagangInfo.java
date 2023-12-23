package com.app.bs1p.Menu.Magang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.bs1p.CallApi.ApiCall;
import com.app.bs1p.CallApi.ApiEndpoint;
import com.app.bs1p.Menu.Navbar.Agrostandar;
import com.app.bs1p.Menu.Navbar.Dashboard;
import com.app.bs1p.Menu.Navbar.Kontak;
import com.app.bs1p.Menu.Navbar.Layanan;
import com.app.bs1p.Menu.Navbar.Organisasi;
import com.app.bs1p.Model.ResponseInfo;
import com.app.bs1p.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MagangInfo extends AppCompatActivity {

    TextView info;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magang_info);

        info = findViewById(R.id.tvMagangInfo);
        api = ApiCall.getApi().create(ApiEndpoint.class);

        api.getInfo().enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(@NonNull Call<ResponseInfo> call, @NonNull Response<ResponseInfo> response) {
                assert response.body() != null;
                String inf = response.body().getHasil().getInformasiMagang();
                info.setText(HtmlCompat.fromHtml(inf, HtmlCompat.FROM_HTML_MODE_LEGACY));
            }
            @Override
            public void onFailure(@NonNull Call<ResponseInfo> call, @NonNull Throwable t) {

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