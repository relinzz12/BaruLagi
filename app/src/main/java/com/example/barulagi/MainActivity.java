package com.example.barulagi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText userName;
    private EditText passTxt;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final String myPREFERENCES = "MyPrefs";
    public static final String Email = "emailKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.username);
        passTxt = findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginbtn);


        sharedPreferences = getSharedPreferences(myPREFERENCES, Context.MODE_PRIVATE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().trim().matches(emailPattern)){
                    if (passTxt.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Password is empty !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Email, userName.getText().toString());
                        editor.apply();
                        openArctivityHome();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Incorrect email and password !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //aded
    public void openArctivityHome(){
        Bundle bundle = new Bundle();
        bundle.putString("dataUsername", userName.getText().toString());
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}