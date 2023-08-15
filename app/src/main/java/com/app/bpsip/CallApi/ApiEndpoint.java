package com.app.bpsip.CallApi;



import com.app.bpsip.Model.ResponseInfo;
import com.app.bpsip.Model.ResponseKunjungan;
import com.app.bpsip.Model.ResponseLab;
import com.app.bpsip.Model.ResponseOrganisasi;
import com.app.bpsip.Model.ResponseKonsul;
import com.app.bpsip.Model.ResponseLabor;
import com.app.bpsip.Model.ResponseMagang;
import com.app.bpsip.Model.ResponsePimpinan;
import com.app.bpsip.Model.ResponseStock;
import com.app.bpsip.Model.ResponseUpbs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndpoint {

    @GET("organisasi/getOrganisasi")
    Call<ResponseOrganisasi> getData();

    @GET("informasi/getInformasi")
    Call<ResponseInfo> getInfo();

    @GET("pimpinan/getPimpinan")
    Call<ResponsePimpinan> getProfil();

    @GET("profile_lab/getProfilelab")
    Call<ResponseLab> getLab();

    @GET("stock_benih/all")
    Call<ResponseStock> getBenih();

    @GET("kunjungan/getKunjungan")
    Call<ResponseKunjungan> getKunjungan();

    @FormUrlEncoded
    @POST("laboratorium/store")
    Call<ResponseLabor> postLabor(@Field("laboratorium_nama") String nama,
                                  @Field("laboratorium_alamat") String alamat,
                                  @Field("laboratorium_instansi") String instansi,
                                  @Field("laboratorium_no_hp") String noHp,
                                  @Field("laboratorium_sampel") String sampel);

    @FormUrlEncoded
    @POST("konsultasi/store")
    Call<ResponseKonsul> postKonsul(@Field("konsultasi_nama") String nama,
                                    @Field("konsultasi_nik") String nik,
                                    @Field("konsultasi_alamat") String alamat,
                                    @Field("konsultasi_institusi") String institusi,
                                    @Field("konsultasi_email") String email,
                                    @Field("konsultasi_no_hp") String noHp,
                                    @Field("konsultasi_pesan") String pesan);
    @FormUrlEncoded
    @POST("magang/store")
    Call<ResponseMagang> postMagang(@Field("magang_nama") String nama,
                                    @Field("magang_nim") String nim,
                                    @Field("magang_sekolah") String sekolah,
                                    @Field("magang_jurusan") String jurusan,
                                    @Field("magang_waktu") String waktu,
                                    @Field("magang_no_hp") String noHp,
                                    @Field("magang_surat") String surat);
    @FormUrlEncoded
    @POST("upbs/store")
    Call<ResponseUpbs> postUpbs(@Field("upbs_nama") String nama,
                                @Field("upbs_nik") String nik,
                                @Field("upbs_alamat") String alamat,
                                @Field("upbs_nama_kelompok_tani") String tani,
                                @Field("upbs_luas_lahan") String lahan,
                                @Field("upbs_komoditas") String komoditas,
                                @Field("upbs_kebutuhan_benih") String benih,
                                @Field("upbs_no_hp") String noHp,
                                @Field("upbs_surat") String surat);

}
