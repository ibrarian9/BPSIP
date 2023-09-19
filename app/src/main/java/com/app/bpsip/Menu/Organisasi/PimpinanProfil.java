package com.app.bpsip.Menu.Organisasi;

import static com.app.bpsip.CallApi.ApiCall.img_url;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Menu.Navbar.Agrostandar;
import com.app.bpsip.Menu.Navbar.Dashboard;
import com.app.bpsip.Menu.Navbar.Kontak;
import com.app.bpsip.Menu.Navbar.Layanan;
import com.app.bpsip.Menu.Navbar.Organisasi;
import com.app.bpsip.Model.ResponsePimpinan;
import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PimpinanProfil extends AppCompatActivity {

    ImageView profil;
    TextView nama, alamat, noHp, email;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pimpinan);

        profil = findViewById(R.id.imgProfil);
        nama = findViewById(R.id.tvNamaProfil);
        alamat = findViewById(R.id.tvAlamat);
        noHp = findViewById(R.id.tvNohp);
        email = findViewById(R.id.tvEmail);

        api = ApiCall.getApi().create(ApiEndpoint.class);
        api.getProfil().enqueue(new Callback<ResponsePimpinan>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ResponsePimpinan> call, @NonNull Response<ResponsePimpinan> response) {
                assert response.body() != null;
                String nProfil = response.body().getHasil().getPimpinanNama();
                String aProfil = response.body().getHasil().getPimpinanAlamat();
                String nhProfil = response.body().getHasil().getPimpinanNoHp();
                String eProfil = response.body().getHasil().getPimpinanEmail();
                String gambar = response.body().getHasil().getPimpinanFoto();

                Picasso.get()
                        .load(img_url + gambar )
                        .into(profil);

                nama.setText("Nama" + "    : " + nProfil);
                alamat.setText("Alamat" + " : " + aProfil);
                noHp.setText("Nomor Handphone" + " : " + nhProfil);
                email.setText("Email" + " : " + eProfil);
            }
            @Override
            public void onFailure(@NonNull Call<ResponsePimpinan> call, @NonNull Throwable t) {
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