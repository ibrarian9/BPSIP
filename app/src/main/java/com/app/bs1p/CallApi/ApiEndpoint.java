package com.app.bs1p.CallApi;



import com.app.bs1p.Model.Hasil;
import com.app.bs1p.Model.ResponseInfo;
import com.app.bs1p.Model.ResponseKunjungan;
import com.app.bs1p.Model.ResponseLab;
import com.app.bs1p.Model.ResponseOrganisasi;
import com.app.bs1p.Model.ResponseKonsul;
import com.app.bs1p.Model.ResponseLabor;
import com.app.bs1p.Model.ResponseMagang;
import com.app.bs1p.Model.ResponsePengaduan;
import com.app.bs1p.Model.ResponsePengunjung;
import com.app.bs1p.Model.ResponsePimpinan;
import com.app.bs1p.Model.ResponseStock;
import com.app.bs1p.Model.ResponseUpbs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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

    @GET("pengunjung/get")
    Call<ResponsePengunjung> getPengunjung();

    @PUT("pengunjung/update/1")
    Call<ResponsePengunjung> updatePengunjung(@Body Hasil updateData);

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
                                @Field("upbs_jabatan") String jabatan,
                                @Field("upbs_luas_lahan") String lahan,
                                @Field("upbs_komoditas") String komoditas,
                                @Field("upbs_kebutuhan_benih") String benih,
                                @Field("upbs_no_hp") String noHp,
                                @Field("upbs_surat") String surat);

    @FormUrlEncoded
    @POST("pengaduan/store")
    Call<ResponsePengaduan> postPengaduan(@Field("pengaduan_nama") String nama,
                                          @Field("pengaduan_nik") String nik,
                                          @Field("pengaduan_alamat") String alamat,
                                          @Field("pengaduan_tempat") String tempat,
                                          @Field("pengaduan_tanggal") String tanggal,
                                          @Field("pengaduan_no_hp") String noHp,
                                          @Field("pengaduan_foto") String foto,
                                          @Field("pengaduan_pengaduan") String pengaduan);



}
