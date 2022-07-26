package com.example.admineliteproperties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    Button btnAddBroker,btnViewBroker,btnViewUsers,btnViewProperties,btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
    }

    private void initialize() {
        btnAddBroker = findViewById(R.id.btnAddBroker);
        btnViewBroker = findViewById(R.id.btnViewBroker);
        btnViewUsers = findViewById(R.id.btnViewUsers);
        btnViewProperties = findViewById(R.id.btnViewProperties);
        btnLogout = findViewById(R.id.btnLogOut);


        btnAddBroker.setOnClickListener(this);
        btnViewBroker.setOnClickListener(this);
        btnViewUsers.setOnClickListener(this);
        btnViewProperties.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAddBroker:
                gotoAddBroker();
                break;
            case R.id.btnViewBroker:
                gotoViewBroker();
                break;
            case R.id.btnViewUsers:
                gotoViewUser();
                break;
            case R.id.btnViewProperties:
                gotoviewProperties();
                break;
            case R.id.btnLogOut:
                finish();
                break;

        }
    }

    private void gotoviewProperties() {
        Intent intent = new Intent(this,PropertyList.class);
        startActivity(intent);
    }

    private void gotoViewUser() {
        Intent intent = new Intent(this,UserList.class);
        startActivity(intent);
    }

    private void gotoViewBroker() {
        Intent intent = new Intent(this,BrokerList.class);
        startActivity(intent);
    }

    private void gotoAddBroker() {
        Intent intent = new Intent(this,AddBroker.class);
        startActivity(intent);
        finish();
    }
}