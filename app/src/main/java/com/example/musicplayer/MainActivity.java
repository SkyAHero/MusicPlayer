package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button land;
    private Button register;
    private CheckBox remberPsd;
    private SharedPreferences pre;
    private SharedPreferences.Editor editor;
    private EditText userEdit;
    private EditText psdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        land = findViewById(R.id.land);
        register = findViewById(R.id.register);
        remberPsd = findViewById(R.id.rember_psd);
        userEdit = findViewById(R.id.user_edit);
        psdEdit = findViewById(R.id.psd_edit);

        pre = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRem = pre.getBoolean("rember_psd",false);
        if (isRem){
            String user = pre.getString("user","");
            String psd = pre.getString("password","");
            userEdit.setText(user);
            psdEdit.setText(psd);
        }
        //LAND
        land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userEdit.getText().toString();
                String password = psdEdit.getText().toString();
                if (user.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "PLEASE INPUT", Toast.LENGTH_SHORT).show();
                } else if (password.length()<8){
                    Toast.makeText(MainActivity.this, "MIN 8", Toast.LENGTH_SHORT).show();
                } else {
                    if (remberPsd.isChecked()){
                        editor.putString("user",user);
                        editor.putString("password",password);
                        editor.putBoolean("rember_psd",true);
                    }
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this,Land.class);
                    startActivity(intent);
                }
            }
        });
        //REGISTER
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
}