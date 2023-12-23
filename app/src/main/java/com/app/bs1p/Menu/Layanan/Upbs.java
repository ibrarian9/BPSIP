package com.app.bs1p.Menu.Layanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bs1p.Menu.Navbar.Agrostandar;
import com.app.bs1p.Menu.Navbar.Dashboard;
import com.app.bs1p.Menu.Upbs.FormBenihBantuan;
import com.app.bs1p.Menu.Navbar.Kontak;
import com.app.bs1p.Menu.Navbar.Layanan;
import com.app.bs1p.Menu.Upbs.UpbsInfo;
import com.app.bs1p.Menu.Upbs.UpbsStock;
import com.app.bs1p.Menu.Navbar.Organisasi;
import com.app.bs1p.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Upbs extends AppCompatActivity {

    ImageView formBenih, info, stock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upbs);

        stock = findViewById(R.id.stock);
        stock.setOnClickListener(view -> startActivity(new Intent(Upbs.this, UpbsStock.class)));

        formBenih = findViewById(R.id.help);
        formBenih.setOnClickListener(view -> startActivity(new Intent(Upbs.this, FormBenihBantuan.class)));

        info = findViewById(R.id.info);
        info.setOnClickListener(view -> startActivity(new Intent(Upbs.this, UpbsInfo.class)));

        BottomNavigationView botNavbar = findViewById(R.id.navbar_upbs);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Upbs.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Upbs.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Upbs.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Upbs.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Upbs.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}