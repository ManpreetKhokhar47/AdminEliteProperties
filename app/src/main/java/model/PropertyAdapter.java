package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admineliteproperties.R;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PropertyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Property> propertyList;
    Property property;

    public PropertyAdapter(Context context, ArrayList<Property> propList) {
        this.context = context;
        this.propertyList = propList;
    }

    @Override
    public int getCount() {
        return propertyList.size();
    }

    @Override
    public Object getItem(int position) {
        return propertyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View oneItem = null;
        ImageView imgProp;
        TextView tvPrice,tvAddress,tvBeds,tvBaths,tvParking;
        Button btnDelete;
        int pos = position;

        LayoutInflater inflater = LayoutInflater.from(context);
        oneItem = inflater.inflate(R.layout.one_property, parent,false);

        imgProp = oneItem.findViewById(R.id.imgPropA);
        tvPrice = oneItem.findViewById(R.id.tvPriceA);
        tvAddress = oneItem.findViewById(R.id.tvAddressA);
        tvBeds = oneItem.findViewById(R.id.tvBedroomsA);
        tvBaths = oneItem.findViewById(R.id.tvBathroomsA);
        tvParking = oneItem.findViewById(R.id.tvparkingA);

        btnDelete = oneItem.findViewById(R.id.btnDeleteP);

        property = (Property) getItem(position);
        String photoStr = property.getSrcImg();

        Picasso.get().load(photoStr).into(imgProp);
        tvPrice.setText("$ "+String.valueOf(property.getPrice()));
        tvAddress.setText(property.getAddress());
        tvBeds.setText(property.getBedroomsCount());
        tvBaths.setText(property.getBathroomCount());
        tvParking.setText(property.getParkingSpaces());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                property = propertyList.get(position);
                String pid = property.getPropertyId();
                FirebaseDatabase.getInstance().getReference("Properties").child(pid).removeValue();
                propertyList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Property deleted", Toast.LENGTH_SHORT).show();
            }
        });



        return oneItem;
    }
}
