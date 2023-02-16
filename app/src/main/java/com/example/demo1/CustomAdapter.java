package com.example.demo1;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private Context context;
    private MainActivity mainActivity;
    private ArrayList product_id, name_Product, price_Product,quantity_Product;

    public CustomAdapter(Context context, MainActivity mainActivity, ArrayList product_id, ArrayList name_Product, ArrayList price_Product) {
        this.context = context;
        this.mainActivity = mainActivity;
        this.product_id = product_id;
        this.name_Product = name_Product;
        this.price_Product = price_Product;
    }
 public CustomAdapter(Context context, MainActivity mainActivity, ArrayList product_id, ArrayList name_Product, ArrayList price_Product, ArrayList quantity_Product) {
     this.context = context;
        this.mainActivity = mainActivity;
        this.product_id = product_id;
        this.name_Product = name_Product;
        this.price_Product = price_Product;
        this.quantity_Product = quantity_Product;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_column, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.name_Product.setText(String.valueOf(name_Product.get(position)));
        holder.price_Product.setText(String.valueOf(price_Product.get(position)));
        holder.quantity_Product.setText(String.valueOf(quantity_Product.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(product_id.get(position)));
                intent.putExtra("Product Name", String.valueOf(name_Product.get(position)));
                intent.putExtra("Price", String.valueOf(price_Product.get(position)));
                intent.putExtra("Quantity", String.valueOf(quantity_Product.get(position)));
                mainActivity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product_id_txt, name_Product, price_Product,quantity_Product;
        LinearLayout mainLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            name_Product = itemView.findViewById(R.id.name_Product_txt);
            price_Product = itemView.findViewById(R.id.price_Product_txt);
            quantity_Product = itemView.findViewById(R.id.quantity_Product_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}

