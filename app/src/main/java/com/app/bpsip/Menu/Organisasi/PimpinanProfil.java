package com.app.bpsip.Menu.Organisasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.ResponsePimpinan;
import com.app.bpsip.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PimpinanProfil extends AppCompatActivity {

    TextView nama, tempat, tanggal;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pimpinan);

        nama = findViewById(R.id.tvNamaProfil);
        tempat = findViewById(R.id.tvTempatProfil);
        tanggal = findViewById(R.id.tvTanggalProfil);

        api = ApiCall.getApi().create(ApiEndpoint.class);
        api.getProfil().enqueue(new Callback<ResponsePimpinan>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ResponsePimpinan> call, @NonNull Response<ResponsePimpinan> response) {
                assert response.body() != null;
                String nProfil = response.body().getHasil().getPimpinanNama();
                String teProfil = response.body().getHasil().getPimpinanTempatLahir();
                String tgProfil = response.body().getHasil().getPimpinanTanggalLahir();

                nama.setText("Nama" + "    : " + nProfil);
                tempat.setText("Tempat" + " : " + teProfil);
                tanggal.setText("Tanggal" + " : " + tgProfil);
            }

            @Override
            public void onFailure(@NonNull Call<ResponsePimpinan> call, @NonNull Throwable t) {

            }
        });
    }
}