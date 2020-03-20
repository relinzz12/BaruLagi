package com.example.barulagi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "mahasiswa";
    public static final String CONTACTS_COLUMN_NIM= "nim";
    public static final String CONTACTS_COLUMN_NAMA = "nama";
    public static final String CONTACTS_COLUMN_NOHP = "nohp";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        final String SQL = "CREATE TABLE "+ CONTACTS_TABLE_NAME+"("+
                CONTACTS_COLUMN_NIM+" TEXT NOT NULL PRIMARY KEY, "+CONTACTS_COLUMN_NAMA+" TEXT NOT NULL, "+CONTACTS_COLUMN_NOHP+" TEXT NOT NULL)";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }

    public boolean insertContact (String nim, String nama, String nohp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim", nim);
        contentValues.put("name", nama);
        contentValues.put("phone", nohp);
        db.insert("mahasiswa", null, contentValues);
        return true;
    }

    public Cursor getData(int nim) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from mahasiswa where nim="+nim+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (String nim, String nama, String nohp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", nama);
        contentValues.put("phone", nohp);
        db.update("mahasiswa", contentValues, "nim = ? ", new String[] { nim } );
        return true;
    }

    public Integer deleteContact (String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("mahasiswa",
                "nim = ? ",
                new String[] { nim });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from mahasiswa", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAMA)));
            res.moveToNext();
        }
        return array_list;
    }
}
