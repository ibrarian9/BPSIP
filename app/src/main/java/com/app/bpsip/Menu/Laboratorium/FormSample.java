package com.app.bpsip.Menu.Laboratorium;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.bpsip.Menu.Navbar.Agrostandar;
import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;

import com.app.bpsip.Menu.Navbar.Dashboard;
import com.app.bpsip.Menu.Navbar.Kontak;
import com.app.bpsip.Menu.Navbar.Layanan;
import com.app.bpsip.Model.ResponseLabor;
import com.app.bpsip.Menu.Navbar.Organisasi;
import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSample extends AppCompatActivity {

    EditText edNama, edInstansi, edAlamat, edNoHp, edSample;
    Button btnSend;
    ApiEndpoint apiEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_sample);

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
            String instansi = edInstansi.getText().toString();
            String noHp = edNoHp.getText().toString();
            String sample = edSample.getText().toString();

            if (nama.equals("")){
                Toast.makeText(FormSample.this, "Nama Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (alamat.equals("")){
                Toast.makeText(FormSample.this, "Alamat Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (instansi.equals("")){
                Toast.makeText(FormSample.this, "Instansi Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (noHp.equals("")){
                Toast.makeText(FormSample.this, "Nomor Hape Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (sample.equals("")){
                Toast.makeText(FormSample.this, "Sample Masih Kosong", Toast.LENGTH_SHORT).show();
            } else {
                Call<ResponseLabor> postLabor = apiEndpoint.postLabor(nama, alamat, instansi, noHp, sample);
                postLabor.enqueue(new Callback<ResponseLabor>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseLabor> call, @NonNull Response<ResponseLabor> response) {
                        Toast.makeText(getApplicationContext(),"Form Berhasil Di Kirim", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FormSample.this, Layanan.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onFailure(@NonNull Call<ResponseLabor> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());
                        Log.e(TAG, "onFailure: "+t.getCause());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Bot Navbar
        BottomNavigationView botNavbar = findViewById(R.id.navbar_sample);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(FormSample.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(FormSample.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(FormSample.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(FormSample.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(FormSample.this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}