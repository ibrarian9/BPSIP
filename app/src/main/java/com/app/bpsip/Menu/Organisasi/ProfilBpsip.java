package com.app.bpsip.Menu.Organisasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.ResponseOrganisasi;
import com.app.bpsip.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilBpsip extends AppCompatActivity {

    TextView profil;
    ApiEndpoint api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_bpsip);

        profil = findViewById(R.id.tvProfilBpsip);
        api = ApiCall.getApi().create(ApiEndpoint.class);
        api.getData().enqueue(new Callback<ResponseOrganisasi>() {
            @Override
            public void onResponse(Call<ResponseOrganisasi> call, Response<ResponseOrganisasi> response) {
                assert response.body() != null;
                String profile = response.body().getHasil().getOrganisasiProfile();
                profil.setText(profile);
            }

            @Override
            public void onFailure(Call<ResponseOrganisasi> call, Throwable t) {

            }
        });

    }
}