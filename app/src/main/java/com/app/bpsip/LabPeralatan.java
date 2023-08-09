package com.app.bpsip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.ResponseLab;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabPeralatan extends AppCompatActivity {

    TextView peralatan;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_peralatan);

        peralatan = findViewById(R.id.tvPeralatan);

        api = ApiCall.getApi().create(ApiEndpoint.class);
        api.getLab().enqueue(new Callback<ResponseLab>() {
            @Override
            public void onResponse(Call<ResponseLab> call, Response<ResponseLab> response) {
                assert response.body() != null;
                String text = response.body().getHasil().getProfileLabPeralatan();
                peralatan.setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY));
            }
            @Override
            public void onFailure(Call<ResponseLab> call, Throwable t) {

            }
        });
    }
}