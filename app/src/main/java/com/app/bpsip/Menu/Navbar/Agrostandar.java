package com.app.bpsip.Menu.Navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Agrostandar extends AppCompatActivity {

    ImageView news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrostandar);

        news = findViewById(R.id.news);
        news.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://bptpriau-ppid.pertanian.go.id/index.php/landing/index"));
            startActivity(i);
        });

        BottomNavigationView botNavBar = findViewById(R.id.navbar_agro);
        botNavBar.setSelectedItemId(R.id.menu_agro);

        botNavBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Agrostandar.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Agrostandar.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Agrostandar.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Agrostandar.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}