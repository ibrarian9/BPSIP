package com.app.bpsip.Menu.Layanan;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
import com.app.bpsip.Model.ResponseKonsul;
import com.app.bpsip.Menu.Navbar.Organisasi;
import com.app.bpsip.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Konsultasi extends AppCompatActivity {

    EditText edNama, edNik, edAlamat, edInstitusi, edEmail, edNoHp, edPesan;
    ApiEndpoint apiEndpoint;
    Button btnKirim;
    String nama, nik, alamat, institusi, email, noHp, pesan;

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
            nama = edNama.getText().toString();
            nik = edNik.getText().toString();
            alamat = edAlamat.getText().toString();
            institusi = edInstitusi.getText().toString();
            email = edEmail.getText().toString();
            noHp = edNoHp.getText().toString();
            pesan = edPesan.getText().toString();

            if (nama.equals("")){
                Toast.makeText(Konsultasi.this, "Nama Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (nik.equals("")) {
                Toast.makeText(Konsultasi.this, "Nik Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (alamat.equals("")) {
                Toast.makeText(Konsultasi.this, "Alamat Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (institusi.equals("")) {
                Toast.makeText(Konsultasi.this,"Institusi Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (email.equals("")) {
                Toast.makeText(Konsultasi.this, "Email Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (noHp.equals("")) {
                Toast.makeText(Konsultasi.this, "Nomor Hape Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (pesan.equals("")) {
                Toast.makeText(Konsultasi.this, "Pesan Masih Kosong", Toast.LENGTH_SHORT).show();
            } else {

                Call<ResponseKonsul> postKonsul = apiEndpoint.postKonsul(nama, nik, alamat, institusi, email, noHp, pesan);
                postKonsul.enqueue(new Callback<ResponseKonsul>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseKonsul> call, @NonNull Response<ResponseKonsul> response) {
                        if (response.isSuccessful()) {
                            sendToWa();
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<ResponseKonsul> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());
                        Log.e(TAG, "onFailure: "+t.getCause());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
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
    private void sendToWa() {
        String contact = "+6282172652675";
        String message =
                "Form Konsultasi " +
                        "%0aNik : " + nik +
                        "%0aNama : " + nama +
                        "%0aAlamat : " + alamat +
                        "%0aInstitusi : " + institusi +
                        "%0aEmail : " + email +
                        "%0aNo Hape : " + noHp +
                        "%0aPesan : " + pesan;

        String url = "https://api.whatsapp.com/send?phone=" + contact + "&text=" + message;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}