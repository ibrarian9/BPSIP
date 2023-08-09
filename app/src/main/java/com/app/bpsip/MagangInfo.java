package com.app.bpsip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Model.ResponseInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MagangInfo extends AppCompatActivity {

    TextView info;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magang_info);

        info = findViewById(R.id.tvMagangInfo);
        api = ApiCall.getApi().create(ApiEndpoint.class);

        api.getInfo().enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                assert response.body() != null;
                String inf = response.body().getHasil().getInformasiMagang();
                info.setText(Html.fromHtml(inf));
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {

            }
        });



    }
}