package com.app.bpsip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.ResponseLab;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabLingkup extends AppCompatActivity {

    TextView lingkup;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_lingkup);

        lingkup = findViewById(R.id.tvLingkup);

        api = ApiCall.getApi().create(ApiEndpoint.class);

        api.getLab().enqueue(new Callback<ResponseLab>() {
            @Override
            public void onResponse(Call<ResponseLab> call, Response<ResponseLab> response) {
                String text = response.body().getHasil().getProfileLabRuangLingkup();
                lingkup.setText(text);
            }
            @Override
            public void onFailure(Call<ResponseLab> call, Throwable t) {

            }
        });

    }
}