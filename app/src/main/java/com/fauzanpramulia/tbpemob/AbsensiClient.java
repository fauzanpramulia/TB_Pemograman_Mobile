package com.fauzanpramulia.tbpemob;

import com.fauzanpramulia.tbpemob.model.MahasiswaItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AbsensiClient {
    @GET("absensi")
    Call<List<MahasiswaItems>> getAbsensi();

    @FormUrlEncoded
    @POST("absensi/insert")
    public void insertAbsen(
            @Field("bp") String bp,
            @Field("nama") String nama,
            @Field("kelas") String kelas,
            @Field("mata_kuliah") String mata_kuliah);
}
