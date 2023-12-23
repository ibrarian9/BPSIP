package com.app.bs1p.Menu.Navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bs1p.Menu.Layanan.Konsultasi;
import com.app.bs1p.Menu.Layanan.Kunjungan;
import com.app.bs1p.Menu.Layanan.Laboratorium;
import com.app.bs1p.Menu.Layanan.Magang;
import com.app.bs1p.Menu.Layanan.Pengaduan;
import com.app.bs1p.Menu.Layanan.Upbs;
import com.app.bs1p.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Layanan extends AppCompatActivity {

    ImageView img1, img2, img3, img4, img5, img6, img7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        // Konsultasi
        img1 = findViewById(R.id.konsultasi);
        img1.setOnClickListener(view -> startActivity(new Intent(Layanan.this, Konsultasi.class)));
        // Labor
        img2 = findViewById(R.id.labor);
        img2.setOnClickListener(view -> startActivity(new Intent(Layanan.this, Laboratorium.class)));
        // Perpus
        img3 = findViewById(R.id.perpus);
        // Kunjungan
        img4 = findViewById(R.id.kunjungan);
        img4.setOnClickListener(view -> startActivity(new Intent(Layanan.this, Kunjungan.class)));
        // Magang
        img5 = findViewById(R.id.magang);
        img5.setOnClickListener(view -> startActivity(new Intent(Layanan.this, Magang.class)));
        // UPBS
        img6 = findViewById(R.id.upbs);
        img6.setOnClickListener(view -> startActivity(new Intent(Layanan.this, Upbs.class)));
        // Pengaduan
        img7 = findViewById(R.id.pengaduan);
        img7.setOnClickListener(view -> startActivity(new Intent(Layanan.this, Pengaduan.class)));

        BottomNavigationView botNavBar = findViewById(R.id.navbar_layanan);
        botNavBar.setSelectedItemId(R.id.menu_layanan);

        botNavBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Layanan.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Layanan.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Layanan.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Layanan.this, Kontak.class));
                return true;
            }
            return false;
        });

    }
}