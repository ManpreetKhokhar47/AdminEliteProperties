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
import model.User;
import model.UserAdapter;


public class UserList extends AppCompatActivity {
    ListView lvUserList;
    ArrayList<User> userList;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("User List");
        initialize();
    }

    private void initialize() {
        lvUserList = findViewById(R.id.lvUserList);
        userList = new ArrayList<User>();
        getUserList();


    }

    private void getUserList() {
        FirebaseDatabase.getInstance().getReference("Users").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    User user;
                    for(DataSnapshot aUser : dataSnapshot.getChildren() ){
                        user = aUser.getValue(User.class);
                        userList.add(user);
                    }
                    userAdapter = new UserAdapter(getApplicationContext(), userList);
                    lvUserList.setAdapter(userAdapter);

                }

            }
        });

    }
}