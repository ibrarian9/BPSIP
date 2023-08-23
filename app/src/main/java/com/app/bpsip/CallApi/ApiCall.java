package com.app.bpsip.CallApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCall {

    public static final String img_url = "https://bsip-riau.com/storage/";

    //    public static final String img_url = "http://192.168.1.7/bpsip_1/public/storage/";
    public static final String magang_file = "https://bsip-riau.com/MagangUpload.php";

    //    public static final String magang_file = "http://192.168.1.7/bpsip_1/app/Http/Controllers/MagangUpload.php";
    public static final String benih_file = "https://bsip-riau.com/BenihUpload.php";

    //    public static final String benih_file = "http://192.168.1.7/bpsip_1/app/Http/Controllers/BenihUpload.php";
    public static final String BASE_URL = "https://bsip-riau.com/api/";

    //    public static final String BASE_URL = "http://192.168.1.7/bpsip_1/public/api/";

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
