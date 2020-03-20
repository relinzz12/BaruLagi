package com.example.barulagi;

import android.provider.BaseColumns;

public class DataMahasiswa {
    public DataMahasiswa(){}
    public static final class mahasiswaEntry implements BaseColumns {
        public static final String TABLE_NAME = "mahasiswa";
        public static final String COLUMN_NIM = "nim";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_NOHP = "nohp";
    }
}
