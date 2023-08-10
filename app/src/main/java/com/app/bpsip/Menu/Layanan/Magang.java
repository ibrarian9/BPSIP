package com.app.bpsip.Menu.Layanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bpsip.Menu.Navbar.Agrostandar;
import com.app.bpsip.Menu.Navbar.Dashboard;
import com.app.bpsip.Menu.Navbar.Kontak;
import com.app.bpsip.Menu.Navbar.Layanan;
import com.app.bpsip.Menu.Magang.FormMagang;
import com.app.bpsip.Menu.Magang.MagangInfo;
import com.app.bpsip.Menu.Navbar.Organisasi;
import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Magang extends AppCompatActivity {

    ImageView form, info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magang);

        form = findViewById(R.id.tv2);
        form.setOnClickListener(view -> startActivity(new Intent(Magang.this, FormMagang.class)));

        info = findViewById(R.id.imgInfo);
        info.setOnClickListener(view -> startActivity(new Intent(Magang.this, MagangInfo.class)));



        BottomNavigationView botNavbar = findViewById(R.id.navbar_magang);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Magang.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Magang.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Magang.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Magang.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Magang.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}