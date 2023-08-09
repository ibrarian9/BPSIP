package com.app.bpsip.CallApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCall {

    public static final String magang_file = "http://192.168.1.7/bpsip/public/app/Http/Controllers/MagangUpload.php";
    public static final String BASE_URL = "http://192.168.1.7/bpsip_1/public/api/";
    private static Retrofit retrofit = null;
    public static Retrofit getApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
