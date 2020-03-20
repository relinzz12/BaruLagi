package com.example.barulagi;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class DatabaseFragment extends Fragment {
    private SQLiteDatabase mydb ;

    private EditText isinim;
    private EditText isinama;
    private EditText isinohp;
    private Button insert;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.activity_database,container,false);




        isinim = v.findViewById(R.id.insertnim);
        isinama = v.findViewById(R.id.insertnama);
        isinohp = v.findViewById(R.id.insertnohp);
        insert = v.findViewById(R.id.tombolinsert);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ContentValues contentValues = new ContentValues();
                contentValues.put("nim", isinim.getText().toString());
                contentValues.put("nama", isinama.getText().toString());
                contentValues.put("nohp", isinohp.getText().toString());
                mydb.insert("mahasiswa",null,contentValues);

                Toast.makeText(getActivity(),"Insert Berhasil", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DBHelper dbhelper = new DBHelper(getContext());
        mydb = dbhelper.getReadableDatabase();
    }

    private Cursor getAllItem(){
        return mydb.query(
                DataMahasiswa.mahasiswaEntry.TABLE_NAME,
                null,null,null,null,null,null
        );
    }
}
