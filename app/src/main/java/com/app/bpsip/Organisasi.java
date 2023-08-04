package com.app.bpsip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Organisasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisasi);

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