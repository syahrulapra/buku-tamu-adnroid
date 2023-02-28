package com.syahrul.bukutamu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Terima, Tolak;
    private String id;
    private TextView iddata, etNama, etAlamat, etPetugas, etKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.TAG_ID);

        Terima = (Button) findViewById(R.id.btnTerima);
        Tolak = (Button) findViewById(R.id.btnTolak);

        iddata = findViewById(R.id.id);
        etNama = findViewById(R.id.nama);
        etAlamat = findViewById(R.id.alamat);
        etPetugas = findViewById(R.id.petugas);
        etKeterangan = findViewById(R.id.keterangan);

        Terima.setOnClickListener(this);
        Tolak.setOnClickListener(this);

        getSiswa();
    }

    private void getSiswa(){
        class GetSiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditActivity.this,"...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showSiswa(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_TAMU,id);
                return s;
            }
        }
        GetSiswa ge = new GetSiswa();
        ge.execute();
    }

    private void showSiswa(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString(Konfigurasi.TAG_NAMA);
            String alamat = c.getString(Konfigurasi.TAG_ALAMAT);
            String petugas = c.getString(Konfigurasi.TAG_PETUGAS);
            String keterangan = c.getString(Konfigurasi.TAG_KETERANGAN);

            etNama.setText(nama);
            etAlamat.setText(alamat);
            etPetugas.setText(petugas);
            etKeterangan.setText(keterangan);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateSiswa(String status){
        final String id = iddata.getText().toString().trim();

        class UpdateSiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditActivity.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.TAG_ID,id);
                hashMap.put(Konfigurasi.TAG_LAINNYA, status);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_UPDATE,hashMap);

                return s;
            }
        }

        UpdateSiswa ue = new UpdateSiswa();
        ue.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == Terima){
            updateSiswa("Terima");
        }

        if(v == Tolak){
            updateSiswa("Tolak");
        }
    }

    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(i);
        finish();
    }
}