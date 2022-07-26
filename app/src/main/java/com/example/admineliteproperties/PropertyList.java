package com.example.admineliteproperties;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import model.Broker;
import model.BrokerAdapter;
import model.Property;
import model.PropertyAdapter;

public class PropertyList extends AppCompatActivity {
    ListView lvPropertyList;
    ArrayList<Property> properties;
    PropertyAdapter propertyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);
        setTitle("Property List");
        initialize();
    }

    private void initialize() {
        lvPropertyList = findViewById(R.id.lvPropertyList);
        properties = new ArrayList<Property>();
        getproperties();
    }

    private void getproperties() {
        FirebaseDatabase.getInstance().getReference("Properties").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Property property;
                    for(DataSnapshot aProp : dataSnapshot.getChildren() ){
                        property = aProp.getValue(Property.class);
                        properties.add(property);
                    }
                    propertyAdapter = new PropertyAdapter(getApplicationContext(), properties);
                    lvPropertyList.setAdapter(propertyAdapter);

                }

            }
        });
    }
}