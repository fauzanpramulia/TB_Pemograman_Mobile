package com.fauzanpramulia.tbpemob;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fauzanpramulia.tbpemob.adapter.MahasiswaAdapter;
import com.fauzanpramulia.tbpemob.model.MahasiswaItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)ProgressBar progressBar;
    MahasiswaAdapter adapter;
    ArrayList<MahasiswaItems> daftarFilm = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MahasiswaAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getAbsensi();
        recyclerView.setAdapter(adapter);
    }


    public void getAbsensi(){


        String API_BASE_URL = "http://10.44.7.170:8000/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AbsensiClient client =  retrofit.create(AbsensiClient.class);

        Call<List<MahasiswaItems>> call = client.getAbsensi();
        call.enqueue(new Callback<List<MahasiswaItems>>() {
            @Override
            public void onResponse(Call<List<MahasiswaItems>> call, Response<List<MahasiswaItems>> response)   {
                //Disini kode kalau berhasil
                Toast.makeText(MainActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
                List<MahasiswaItems> absenList = response.body();

                adapter.setDataMahasiswa((ArrayList<MahasiswaItems>) absenList);
            }

            @Override
            public void onFailure(Call<List<MahasiswaItems>> call, Throwable t) {
                //Disini kode kalau error
                Toast.makeText(MainActivity.this, "Gagal Load Data", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }


    //menampilkan menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addAbsen:
                Intent addDataActivityIntent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(addDataActivityIntent);
                break;

            case R.id.refreshAbsen:

                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
