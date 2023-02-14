package com.example.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class TwoColumn_ListAdapter  extends ArrayAdapter<User> {

    private LayoutInflater mInflater;
    private ArrayList<User> users;
    private  int mViewResourceId;

    public TwoColumn_ListAdapter (Context context, int textViewResourceId, ArrayList<User> users){
        super(context,textViewResourceId,users);
        this.users = users;
        mInflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView = mInflater.inflate(mViewResourceId,null);
        User user = users.get(position);

        if(user!=null){
            TextView product = (TextView) convertView.findViewById(R.id.textproductname);
            TextView price = (TextView) convertView.findViewById(R.id.textprice);
            //TextView quantity = (TextView) convertView.findViewById(R.id.textquality);

            if (product!=null){
                product.setText((user.getProduct()));
            }
            if (price!=null){
                price.setText((user.getPrice()));
            }
            /*if (quantity!=null){
               quantity.setText((user.getQuantity()));
            }*/


        }
        return convertView;
    }

}