package com.fauzanpramulia.tbpemob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fauzanpramulia.tbpemob.model.MahasiswaItems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertActivity extends AppCompatActivity {
    @BindView(R.id.editBp) EditText editBp;
    @BindView(R.id.editNama) EditText editNama;
    @BindView(R.id.editMataKuliah) EditText editMataKuliah;
    @BindView(R.id.editKelas) EditText editKelas;
    @BindView(R.id.save) Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ButterKnife.bind(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertAbsen();
            }
        });
    }

    private void insertAbsen(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        String API_BASE_URL = "http://10.44.7.170:8000/api/";

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(API_BASE_URL) //Setting the Root URL
                .addConverterFactory(GsonConverterFactory.create())
                .build(); //Finally building the adapter

        //Creating object for our interface
        AbsensiClient api = adapter.create(AbsensiClient.class);

        api.insertAbsen(
                editBp.getText().toString(),
                editNama.getText().toString(),
                editKelas.getText().toString(),
                editMataKuliah.getText().toString()
        ).enqueue(new Callback<List<MahasiswaItems>>() {
            @Override
            public void onResponse(Call<List<MahasiswaItems>> call, Response<List<MahasiswaItems>> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(InsertActivity.this, ""+response.body().toString(), Toast.LENGTH_SHORT).show();

                    Log.i("Yes", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<MahasiswaItems>> call, Throwable t) {
                Log.e("Oh no", "Unable to submit post to API.");
            }
        });
    }

}
