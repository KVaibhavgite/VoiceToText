package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText product_name_input,price_input;
    Button submit,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        product_name_input = findViewById(R.id.name_input);
        price_input = findViewById(R.id.price_input);

       submit = findViewById(R.id.add_button);
        view= findViewById(R.id.view_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB = new DBHelper(AddActivity.this);
                DB.addProduct(product_name_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()));
/*
                String productTXT = product_name_input.getText().toString();
                String priceTXT = price_input.getText().toString();
                //String quantityTXT = quantity.getText().toString();



                Boolean checkinsertdata = DB.insertproductdata(productTXT,priceTXT);
                if (checkinsertdata==true){
                    Toast.makeText(AddActivity.this, "New entry inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddActivity.this, "Already entry exists", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        view.setOnClickListener(v -> {
            Intent int0 = new Intent(AddActivity.this,View_List_Content.class);
            startActivity(int0);
            Toast.makeText(AddActivity.this, " database", Toast.LENGTH_SHORT).show();
        });


    }
}
