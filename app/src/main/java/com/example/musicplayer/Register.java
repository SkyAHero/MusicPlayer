package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.musicplayer.database.UserDatabaseHelper;

public class Register extends AppCompatActivity {

    private Button registerSuc;
    private UserDatabaseHelper userDatabaseHelper;
    private EditText userReg;
    private EditText psdReg;
    private EditText emailReg;
    private EditText phoneReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerSuc = findViewById(R.id.register_suc);
        userReg = findViewById(R.id.user_reg);
        psdReg = findViewById(R.id.psd_reg);
        emailReg = findViewById(R.id.email_reg);
        phoneReg = findViewById(R.id.phone_reg);

        registerSuc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")  //getColumnIndex返回值是从-1开始的，不符合getString的参数，忽略警告
            @Override
            public void onClick(View v) {
                String user = userReg.getText().toString();
                String password = psdReg.getText().toString();
                String email = emailReg.getText().toString();
                String phone = phoneReg.getText().toString();
                if (user.equals("") || password.equals("") || email.equals("") || phone.equals("")) {
                    Toast.makeText(Register.this, "PLEASE INPUT", Toast.LENGTH_SHORT).show();
                } else {
                    userDatabaseHelper = new UserDatabaseHelper(Register.this, "Users.db", null, 1);
                    SQLiteDatabase sqLiteDatabase = userDatabaseHelper.getWritableDatabase();
                    Cursor cursor = sqLiteDatabase.rawQuery("select user from Users where user=" + "\"" + user + "\"", null);
                    cursor.moveToFirst();
                    if (cursor.getString(cursor.getColumnIndex("user"))!=null){
                        Toast.makeText(Register.this, "REPEAT", Toast.LENGTH_SHORT).show();
                        cursor.close();
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("user",user);
                        contentValues.put("password",password);
                        contentValues.put("email",email);
                        contentValues.put("phone",phone);
                        sqLiteDatabase.insert("Users",null,contentValues);
                        Intent intent = new Intent(Register.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}

