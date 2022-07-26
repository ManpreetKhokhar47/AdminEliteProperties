package com.example.admineliteproperties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import model.Broker;

public class AddBroker extends AppCompatActivity implements View.OnClickListener {

    EditText edFirstName, edLastName, edContactNo, edEmail, edExp;
    Button btnRegister,btnCancel;

    DatabaseReference dbBroker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_broker);
        initialize();
    }

    private void initialize() {
        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edContactNo = findViewById(R.id.edContactNo);
        edEmail = findViewById(R.id.edEmailId);
        edExp = findViewById(R.id.edExp);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        btnCancel = findViewById(R.id.btnCancelBroker);
        btnCancel.setOnClickListener(this);

        dbBroker = FirebaseDatabase.getInstance().getReference("Brokers");




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                RegisterBroker();
                break;
            case R.id.btnCancelBroker:
                this.finish();
                Intent intent = new Intent(this,Dashboard.class);
                startActivity(intent);
                break;
        }

    }

    private void RegisterBroker() {
        String firstName = edFirstName.getText().toString().trim();
        String lastName = edLastName.getText().toString().trim();
        String contactNo = edContactNo.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String exp = edExp.getText().toString().trim();

        if(ValidateDetails(firstName, lastName, contactNo, email,exp))
        {
            String uid = UUID.randomUUID().toString();
            Broker broker = new Broker(firstName,lastName,contactNo,email,exp,uid);
            
            dbBroker.child(uid).setValue(broker).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(AddBroker.this, "Broker registered", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                    else {
                        Toast.makeText(AddBroker.this, "Failed to Register Broker", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    private void clearFields() {
        edFirstName.setText(null);
        edLastName.setText(null);
        edContactNo.setText(null);
        edEmail.setText(null);
        edExp.setText(null);

    }

    private boolean ValidateDetails(String firstName, String lastName, String contactNo, String email, String exp) {
        if(firstName.isEmpty()){
            edFirstName.setError("First name is required");
            edFirstName.requestFocus();
            return false;
        }
        if(lastName.isEmpty()){
            edLastName.setError("Last name is required");
            edLastName.requestFocus();
            return false;
        }
        if(contactNo.isEmpty()){
            edContactNo.setError("Contact number is required");
            edContactNo.requestFocus();
            return false;
        }
        if(email.isEmpty()){
            edEmail.setError("Email id is required");
            edEmail.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edEmail.setError("Enter a valid emial id");
            edEmail.requestFocus();
            return false;
        }
        if(exp.isEmpty()){
            edExp.setError("Experience is required");
            edExp.requestFocus();
            return false;
        }
        return true;
    }

}