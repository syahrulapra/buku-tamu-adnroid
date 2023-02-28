package com.syahrul.bukutamu;

public class Konfigurasi {

    public static final String IP = "http://192.168.172.1";
    public static final String URL_LOGIN= IP+ "/bukutamu/data/login.php";
    public static final String URL_GET_DATA= IP+ "/bukutamu/data/listtamu.php";
    public static final String URL_ADD= IP+ "/bukutamu/data/tambahtamu.php";
    public static final String URL_GET_TAMU = IP+ "/bukutamu/data/tampiltamu.php?idtamu=";
    public static final String URL_UPDATE = IP+ "/bukutamu/data/updatetamu.php";
    public static final String URL_DELETE = IP+ "/bukutamu/data/hapustamu.php?idtamu=";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_PETUGAS = "petugas";
    public static final String KEY_KETERANGAN = "keterangan";
    public static final String KEY_LAINNYA = "lainnya";
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "idtamu";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_PETUGAS = "petugas";
    public static final String TAG_KETERANGAN = "keterangan";
    public static final String TAG_LAINNYA = "lainnya";
}
