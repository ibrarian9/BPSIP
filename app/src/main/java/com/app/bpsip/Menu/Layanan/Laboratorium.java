package com.app.bpsip.Menu.Layanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bpsip.Menu.Navbar.Agrostandar;
import com.app.bpsip.Menu.Navbar.Dashboard;
import com.app.bpsip.Menu.Navbar.Kontak;
import com.app.bpsip.Menu.Navbar.Layanan;
import com.app.bpsip.Menu.Laboratorium.FormSample;
import com.app.bpsip.Menu.Laboratorium.LabLingkup;
import com.app.bpsip.Menu.Laboratorium.LabPeralatan;
import com.app.bpsip.Menu.Laboratorium.LabTarif;
import com.app.bpsip.Menu.Navbar.Organisasi;
import com.app.bpsip.Menu.Organisasi.StrukturBpsip;
import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Laboratorium extends AppCompatActivity {

    ImageView lingkup, form, tarif, alat, struktur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorium);

        struktur = findViewById(R.id.tv2);
        struktur.setOnClickListener(view -> startActivity(new Intent(Laboratorium.this, StrukturBpsip.class)));

        lingkup = findViewById(R.id.lingkup);
        lingkup.setOnClickListener(view -> startActivity(new Intent(Laboratorium.this, LabLingkup.class)));

        tarif = findViewById(R.id.tv4);
        tarif.setOnClickListener(view -> startActivity(new Intent(Laboratorium.this, LabTarif.class)));

        alat = findViewById(R.id.tv3);
        alat.setOnClickListener(view -> startActivity(new Intent(Laboratorium.this, LabPeralatan.class)));

        form = findViewById(R.id.tv5);
        form.setOnClickListener(view -> startActivity(new Intent(Laboratorium.this, FormSample.class)));

        BottomNavigationView botNavbar = findViewById(R.id.navbar_labor);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Laboratorium.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Laboratorium.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Laboratorium.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Laboratorium.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Laboratorium.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}