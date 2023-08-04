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
import com.app.bpsip.Model.Konsul;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Konsultasi extends AppCompatActivity {

    EditText edNama, edNik, edAlamat, edInstitusi, edEmail, edNoHp, edPesan;
    ApiEndpoint apiEndpoint;
    Button btnKirim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        edNama = findViewById(R.id.konsulNama);
        edNik = findViewById(R.id.konsulNik);
        edAlamat = findViewById(R.id.konsulAlamat);
        edInstitusi = findViewById(R.id.konsulInstitusi);
        edEmail = findViewById(R.id.konsulEmail);
        edNoHp = findViewById(R.id.konsulNohp);
        edPesan = findViewById(R.id.komen);

        apiEndpoint = ApiCall.getApi().create(ApiEndpoint.class);

        btnKirim = findViewById(R.id.btnKirim);
        btnKirim.setOnClickListener(view -> {
            Call<Konsul> postKonsulCall = apiEndpoint.postKonsul(edNama.getText().toString(), edNik.getText().toString(),
                    edAlamat.getText().toString(), edInstitusi.getText().toString(), edEmail.getText().toString(),
                    edNoHp.getText().toString(), edPesan.getText().length());

            postKonsulCall.enqueue(new Callback<Konsul>() {
                @Override
                public void onResponse(Call<Konsul> call, Response<Konsul> response) {
                    Log.e(TAG, "onResponse: "+response.message());
                    Log.e(TAG, "onResponse: "+response.code());
                    Toast.makeText(getApplicationContext(),"Form Berhasil Di Kirim", Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(Call<Konsul> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage());
                    Log.e(TAG, "onFailure: "+t.getCause());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        });


        BottomNavigationView botNavbar = findViewById(R.id.navbar_konsultasi);

        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(Konsultasi.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(Konsultasi.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(Konsultasi.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(Konsultasi.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(Konsultasi.this, Kontak.class));
                return true;
            }
            return false;
        });


    }
}