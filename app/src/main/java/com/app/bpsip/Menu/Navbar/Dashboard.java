package com.app.bpsip.Menu.Navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.app.bpsip.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Dashboard extends AppCompatActivity {

    TextView tv1, tv2, pengunjung;
    int num;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView botNavbar = findViewById(R.id.navbar_dashboard);
        botNavbar.setSelectedItemId(R.id.menu_home);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        YoYo.with(Techniques.Flash).duration(700).repeat(5).playOn(tv1);
        YoYo.with(Techniques.Flash).duration(700).repeat(5).playOn(tv2);

        SharedPreferences setting = getApplicationContext().getSharedPreferences("Kunjungan", 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putInt("Kunjungan", 1 + 1);
        editor.apply();
        num = setting.getInt("Kunjungan", 1 + 1);

        pengunjung = findViewById(R.id.pengunjung);
        pengunjung.setText("Anda adalah Pengunjung ke = " + num);

        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Dashboard.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Dashboard.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Dashboard.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Dashboard.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}