package com.app.bpsip.Menu.Navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bpsip.Menu.Organisasi.PimpinanProfil;
import com.app.bpsip.Menu.Organisasi.ProfilBpsip;

import com.app.bpsip.Menu.Organisasi.TugasDanFungsi;
import com.app.bpsip.Menu.Organisasi.VisiDanMisi;
import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Organisasi extends AppCompatActivity {

    ImageView pimpinan, profile, visiDanMisi, tugas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisasi);

        tugas = findViewById(R.id.tugas);
        tugas.setOnClickListener(view -> startActivity(new Intent(Organisasi.this, TugasDanFungsi.class)));

        profile = findViewById(R.id.profilBpsip);
        profile.setOnClickListener(view -> startActivity(new Intent(Organisasi.this, ProfilBpsip.class)));

        visiDanMisi = findViewById(R.id.imgVisi);
        visiDanMisi.setOnClickListener(view -> startActivity(new Intent(Organisasi.this, VisiDanMisi.class)));

        pimpinan = findViewById(R.id.pemimpin);
        pimpinan.setOnClickListener(view -> startActivity(new Intent(Organisasi.this, PimpinanProfil.class)));

        BottomNavigationView botNavbar = findViewById(R.id.navbar_organisasi);
        botNavbar.setSelectedItemId(R.id.menu_organisasi);

        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Organisasi.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Organisasi.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Organisasi.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Organisasi.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}