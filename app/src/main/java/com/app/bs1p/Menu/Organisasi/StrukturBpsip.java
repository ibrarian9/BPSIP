package com.app.bs1p.Menu.Organisasi;

import static com.app.bs1p.CallApi.ApiCall.img_url;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bs1p.CallApi.ApiCall;
import com.app.bs1p.CallApi.ApiEndpoint;
import com.app.bs1p.Menu.Navbar.Agrostandar;
import com.app.bs1p.Menu.Navbar.Dashboard;
import com.app.bs1p.Menu.Navbar.Kontak;
import com.app.bs1p.Menu.Navbar.Layanan;
import com.app.bs1p.Menu.Navbar.Organisasi;
import com.app.bs1p.Model.ResponseOrganisasi;
import com.app.bs1p.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StrukturBpsip extends AppCompatActivity {

    ImageView struktur;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struktur_bpsip);

        struktur = findViewById(R.id.imgStruktur);

        api = ApiCall.getApi().create(ApiEndpoint.class);
        api.getData().enqueue(new Callback<ResponseOrganisasi>() {
            @Override
            public void onResponse(@NonNull Call<ResponseOrganisasi> call, @NonNull Response<ResponseOrganisasi> response) {
                assert response.body() != null;
                String image = response.body().getHasil().getOrganisasiStruktur();

                Picasso.get().load(img_url + image)
                        .into(struktur);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseOrganisasi> call, @NonNull Throwable t) {

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