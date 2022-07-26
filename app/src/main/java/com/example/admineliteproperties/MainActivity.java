package com.example.admineliteproperties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edUserName,edPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUserName = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edUserPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                gotoDashboard();
                break;
        }
        
    }

    private void gotoDashboard() {
        if(edUserName.getText().toString().equals("admin") && edPass.getText().toString().equals("admin") ){
            Intent intent = new Intent(this,Dashboard.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}