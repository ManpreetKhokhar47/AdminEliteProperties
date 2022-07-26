package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admineliteproperties.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> userList;
    User user;

    public UserAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View oneItem = null;
        TextView tvName,tvPhno,tvEmailId;
        Button btnDelete;
        int pos = position;

        LayoutInflater inflater = LayoutInflater.from(context);
        oneItem = inflater.inflate(R.layout.one_user,parent,false);

        tvName = oneItem.findViewById(R.id.tvUserName);
        tvPhno = oneItem.findViewById(R.id.tvUserPhNo);
        tvEmailId = oneItem.findViewById(R.id.tvUserEmailId);
        btnDelete = oneItem.findViewById(R.id.btnUserDelete);

        user = (User)getItem(position);

        tvName.setText(user.getFirstName()+" "+ user.getLastName());
        tvPhno.setText("Ph : "+user.getContactNo());
        tvEmailId.setText("Email Id : "+user.getEmailId());


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "User Record Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        return oneItem;
    }
}
