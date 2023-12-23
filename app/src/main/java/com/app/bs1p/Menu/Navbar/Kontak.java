package com.app.bs1p.Menu.Navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.bs1p.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Kontak extends AppCompatActivity {

    ImageView fb, ig, x, yt, tiktok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        fb = findViewById(R.id.facebook);
        ig = findViewById(R.id.instagram);
        x = findViewById(R.id.x);
        yt = findViewById(R.id.youtube);
        tiktok = findViewById(R.id.tiktok);

        fb.setOnClickListener(view -> {
            String url = "https://www.facebook.com/profile.php?id=100090443922393";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        yt.setOnClickListener(view -> {
            String url = "https://www.youtube.com/@bsipriauchannel";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        BottomNavigationView botNavbar = findViewById(R.id.navbar_kontak);
        botNavbar.setSelectedItemId(R.id.menu_kontak);

        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Kontak.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Kontak.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Kontak.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Kontak.this, Agrostandar.class));
                return true;
            } else return itemId == R.id.menu_kontak;
        });
    }
}