package com.example.barulagi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        new DatabaseFragment();

        setContentView(R.layout.fragment_database);

    }


}
