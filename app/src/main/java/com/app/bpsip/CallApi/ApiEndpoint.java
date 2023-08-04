package com.app.bpsip.CallApi;


import com.app.bpsip.Magang;
import com.app.bpsip.Model.Konsul;
import com.app.bpsip.Model.Labor;
import com.app.bpsip.Model.Response;
import com.app.bpsip.Model.Upbs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndpoint {
    @FormUrlEncoded
    @POST("laboratorium/store")
    Call<Response> postLabor(@Field("laboratorium_nama") String nama,
                             @Field("laboratorium_alamat") String alamat,
                             @Field("laboratorium_instansi") String instansi,
                             @Field("laboratorium_no_hp") String noHp,
                             @Field("laboratorium_sampel") String sampel);

    @FormUrlEncoded
    @POST("konsultasi/store")
    Call<Konsul> postKonsul(@Field("konsultasi_nama") String nama,
                            @Field("konsultasi_nik") String nik,
                            @Field("konsultasi_alamat") String alamat,
                            @Field("konsultasi_institusi") String institusi,
                            @Field("konsultasi_email") String email,
                            @Field("konsultasi_no_hp") String noHp,
                            @Field("konsultasi_pesan") int pesan);

    @FormUrlEncoded
    @POST("magang/store")
    Call<Magang> postMagang(@Field("magang_nama") String nama,
                            @Field("magang_nim") String nim,
                            @Field("magang_sekolah") String sekolah,
                            @Field("magang_jurusan") String jurusan,
                            @Field("magang_waktu") String waktu,
                            @Field("magang_no_hp") String noHp,
                            @Field("magang_surat") String surat);

    @FormUrlEncoded
    @POST("upbs/store")
    Call<Upbs> postUpbs(@Field("upbs_nama") String nama,
                        @Field("upbs_nik") String nik,
                        @Field("upbs_alamat") String alamat,
                        @Field("upbs_nama_kelompok_tani") String tani,
                        @Field("upbs_jabatan") String jabatan,
                        @Field("upbs_luas_lahan") String lahan,
                        @Field("upbs_komoditas") String komoditas,
                        @Field("upbs_kebutuhan_benih") String benih,
                        @Field("upbs_no_hp") String noHp,
                        @Field("upbs_surat") String surat);

}
