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

public class BrokerList extends AppCompatActivity {
    ListView lvBrokerList;
    ArrayList<Broker> brokersList;
    BrokerAdapter brokerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_list);
        initialize();
    }

    private void initialize() {
        lvBrokerList = findViewById(R.id.lvBrokerList);
        brokersList = new ArrayList<Broker>();
        getbrokerlist();
        setTitle("Broker List");

    }

    private void getbrokerlist() {
        FirebaseDatabase.getInstance().getReference("Brokers").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Broker broker;
                    for(DataSnapshot aBro : dataSnapshot.getChildren() ){
                        broker = aBro.getValue(Broker.class);
                        brokersList.add(broker);
                    }
                    brokerAdapter = new BrokerAdapter(getApplicationContext(), brokersList);
                    lvBrokerList.setAdapter(brokerAdapter);

                }

            }
        });
    }
}