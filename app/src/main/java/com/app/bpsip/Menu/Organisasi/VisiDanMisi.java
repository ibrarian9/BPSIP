package com.app.bpsip.Menu.Organisasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.ResponseOrganisasi;
import com.app.bpsip.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiDanMisi extends AppCompatActivity {

    TextView visiIsi, misiIsi;
    ApiEndpoint api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visi_dan_misi);

        visiIsi = findViewById(R.id.tvVisiIsi);
        misiIsi = findViewById(R.id.tvMisiIsi);

        api = ApiCall.getApi().create(ApiEndpoint.class);

        api.getData().enqueue(new Callback<ResponseOrganisasi>() {
            @Override
            public void onResponse(Call<ResponseOrganisasi> call, Response<ResponseOrganisasi> response) {
                String visi = response.body().getHasil().getOrganisasiVisi();
                String misi = response.body().getHasil().getOrganisasiMisi();

                visiIsi.setText(visi);
                misiIsi.setText(Html.fromHtml(misi));
            }

            @Override
            public void onFailure(Call<ResponseOrganisasi> call, Throwable t) {

            }
        });
    }
}