package com.syahrul.bukutamu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class FormActivity extends AppCompatActivity {

    private TextInputEditText etNama, etAlamat, etPetugas, etKeterangan;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etNama = findViewById(R.id.nama);
        etAlamat = findViewById(R.id.alamat);
        etPetugas = findViewById(R.id.petugas);
        etKeterangan = findViewById(R.id.keterangan);
        submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nama = etNama.getText().toString().trim();
                final String alamat = etAlamat.getText().toString().trim();
                final String petugas = etPetugas.getText().toString().trim();
                final String keterangan = etKeterangan.getText().toString().trim();

                class TambahData extends AsyncTask<Void, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(FormActivity.this,
                                "Menambahkan...", "Tunggu...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(FormActivity.this, s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put(Konfigurasi.KEY_NAMA, nama);
                        params.put(Konfigurasi.KEY_ALAMAT, alamat);
                        params.put(Konfigurasi.KEY_PETUGAS, petugas);
                        params.put(Konfigurasi.KEY_KETERANGAN, keterangan);

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                        return res;
                    }
                }

                TambahData td = new TambahData();
                td.execute();

            }
        });
    }
}