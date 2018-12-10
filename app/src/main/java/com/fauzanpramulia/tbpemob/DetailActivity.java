package com.fauzanpramulia.tbpemob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fauzanpramulia.tbpemob.model.MahasiswaItems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.imageDetail) ImageView imageDetail;
    @BindView(R.id.textDetailBp) TextView textDetailBp;
    @BindView(R.id.textDetailNama) TextView textDetailNama;
    @BindView(R.id.textDetailMatkul) TextView textDetailMatkul;
    @BindView(R.id.textTanggalDetail) TextView textDetailTanggal;
    @BindView(R.id.textWaktuDetail) TextView textDetailWaktu;
    @BindView(R.id.textDetailKelas) TextView textDetailKelas;

    public static String EXTRA_DETAIL_ABSEN = "extra_detail_absen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        MahasiswaItems mahasiswa= getIntent().getParcelableExtra(EXTRA_DETAIL_ABSEN);

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm a");
        Date date=null;
        try {
            date= f.parse(mahasiswa.getCreated_at());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String tanggalString = formatter.format(date);
        String waktuString = formatter2.format(date);

        textDetailBp.setText(mahasiswa.getBp());
        textDetailNama.setText(mahasiswa.getNama());
        textDetailMatkul.setText(mahasiswa.getMata_kuliah());
        textDetailTanggal.setText(tanggalString);
        textDetailWaktu.setText(waktuString);
        textDetailKelas.setText(mahasiswa.getKelas());

    }
}
