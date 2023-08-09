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

public class UpbsInfo extends AppCompatActivity {

    TextView info;
    ApiEndpoint api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_upbs);

        info = findViewById(R.id.tvInfoUpbs);
        api = ApiCall.getApi().create(ApiEndpoint.class);

        api.getInfo().enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                assert response.body() != null;
                String inf = response.body().getHasil().getInformasiUpbs();
                info.setText(Html.fromHtml(inf));
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {

            }
        });


    }
}