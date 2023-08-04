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
import com.app.bpsip.Model.Labor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegSample extends AppCompatActivity {

    EditText edNama, edInstansi, edAlamat, edNoHp, edSample;
    Button btnSend;
    ApiEndpoint apiEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_sample);

        // Bot Navbar
        BottomNavigationView botNavbar = findViewById(R.id.navbar_sample);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(RegSample.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(RegSample.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(RegSample.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(RegSample.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(RegSample.this, Kontak.class));
                return true;
            }
            return false;
        });

        // Get Id
        edNama = findViewById(R.id.labNama);
        edAlamat = findViewById(R.id.labAlamat);
        edInstansi = findViewById(R.id.labInstitusi);
        edNoHp = findViewById(R.id.labNohp);
        edSample = findViewById(R.id.labSample);

        apiEndpoint = ApiCall.getApi().create(ApiEndpoint.class);

        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(view -> {

            String nama = edNama.getText().toString();
            String alamat = edAlamat.getText().toString();
            String Instansi = edInstansi.getText().toString();
            String noHp = edNoHp.getText().toString();
            String sample = edSample.getText().toString();

            Call<Labor> postLaborCall = apiEndpoint.postLabor();

            postLaborCall.enqueue(new Callback<Labor>() {
                @Override
                public void onResponse(Call<Labor> call, Response<Labor> response) {
                    Log.e(TAG, "onResponse: "+response.message());
                    Log.e(TAG, "onResponse: "+response.code());
                    Toast.makeText(getApplicationContext(),"Form Berhasil Di Kirim", Toast.LENGTH_LONG).show();
                    finish();
                }
                @Override
                public void onFailure(Call<Labor> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage());
                    Log.e(TAG, "onFailure: "+t.getCause());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}