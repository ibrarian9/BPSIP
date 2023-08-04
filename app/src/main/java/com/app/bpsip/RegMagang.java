package com.app.bpsip;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegMagang extends AppCompatActivity {

    EditText edNama, edNim, edSekolah, edJurusan, edNoHp, edWaktu, edSurat;
    ApiEndpoint apiEndpoint;
    Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_magang);

        edNama = findViewById(R.id.magangNama);
        edNim = findViewById(R.id.magangNim);
        edSekolah = findViewById(R.id.magangSekolah);
        edJurusan = findViewById(R.id.magangJurusan);
        edNoHp = findViewById(R.id.magangNoHp);
        edWaktu = findViewById(R.id.magangWaktu);

        apiEndpoint = ApiCall.getApi().create(ApiEndpoint.class);

        btnKirim = findViewById(R.id.send);
        btnKirim.setOnClickListener(view -> {
            Call<Magang> postMagangCall = apiEndpoint.postMagang();

            postMagangCall.enqueue(new Callback<Magang>() {
                @Override
                public void onResponse(Call<Magang> call, Response<Magang> response) {
                    Log.e(TAG, "onResponse: "+response.message());
                    Log.e(TAG, "onResponse: "+response.code());
                    Toast.makeText(getApplicationContext(),"Form Berhasil Di Kirim", Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(Call<Magang> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage());
                    Log.e(TAG, "onFailure: "+t.getCause());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        });


        BottomNavigationView botNavbar = findViewById(R.id.navbar_regMagang);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(RegMagang.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(RegMagang.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(RegMagang.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(RegMagang.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(RegMagang.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}