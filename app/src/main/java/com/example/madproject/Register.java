package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject.R;

public class Register extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    Button reg;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed1 = findViewById(R.id.fName);
        ed2 = findViewById(R.id.lName);
        ed3 = findViewById(R.id.phone);
        ed4 = findViewById(R.id.email);
        ed5 = findViewById(R.id.pass);
        ed6 = findViewById(R.id.cnfpass);
        reg = findViewById(R.id.regbtn);
        log = findViewById(R.id.LoginLink);

        log.setMovementMethod(LinkMovementMethod.getInstance());

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert();

            }
        });
    }
    public void insert(){
        try {
            String fName = ed1.getText().toString();
            String lName = ed2.getText().toString();
            String phone = ed3.getText().toString();
            String email = ed4.getText().toString();
            String pass  = ed5.getText().toString();
            String confpass = ed6.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("toy", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT,fName VARCHAR,lName VARCHAR,phone VARCHAR,email VARCHAR,pass VARCHAR,confpass VARCHAR)");

            if(!pass.equals(confpass)){

                Toast.makeText(Register.this,"Password not maching",Toast.LENGTH_LONG).show();
            }
            else{
                String sql = "insert into user (fName,lName,phone,email,pass,confpass)values(?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1,fName);
                statement.bindString(2,lName);
                statement.bindString(3,phone);
                statement.bindString(4,email);
                statement.bindString(5,pass);
                statement.bindString(6,confpass);
                statement.execute();
                Toast.makeText(this,"Successfully Regester",Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");
                ed6.setText("");

                ed1.requestFocus();
            }
        }
        catch (Exception ex){

        }
    }
}
