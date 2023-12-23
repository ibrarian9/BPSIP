package com.app.bs1p.Menu.Layanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bs1p.Menu.Kunjungan.Pengujian;
import com.app.bs1p.Menu.Kunjungan.Perpustakaan;
import com.app.bs1p.Menu.Kunjungan.TamanAgro;
import com.app.bs1p.Menu.Navbar.Agrostandar;
import com.app.bs1p.Menu.Navbar.Dashboard;
import com.app.bs1p.Menu.Navbar.Kontak;
import com.app.bs1p.Menu.Navbar.Layanan;
import com.app.bs1p.Menu.Navbar.Organisasi;
import com.app.bs1p.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Kunjungan extends AppCompatActivity {

    ImageView Taman, Perpus, pengujian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunjungan);

        Taman = findViewById(R.id.tvTaman);
        Taman.setOnClickListener(view -> startActivity(new Intent(Kunjungan.this, TamanAgro.class)));

        Perpus = findViewById(R.id.tvPerpus);
        Perpus.setOnClickListener(view -> startActivity(new Intent(Kunjungan.this, Perpustakaan.class)));

        pengujian = findViewById(R.id.tvPengujian);
        pengujian.setOnClickListener(view -> startActivity(new Intent(Kunjungan.this, Pengujian.class)));

        BottomNavigationView botNavbar = findViewById(R.id.navbar_kunjungan);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Kunjungan.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Kunjungan.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Kunjungan.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Kunjungan.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Kunjungan.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}