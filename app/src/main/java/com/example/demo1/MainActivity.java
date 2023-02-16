package com.example.demo1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ImageView iv_mic1;
    private TextView tv_Speech_to_text;
    private static final int REQUEST_CODE_SPEECH_INPUT = 0;

    String mAnswer = "";
    ArrayList<String> result;
    ArrayAdapter<String> adapter ;



    int num = 0;
    LinkedList<String> ll= new LinkedList<>();
    DBHelper DB;
    ArrayList<String> product_id, name_Product, price_Product,quantity_Product;
    CustomAdapter customAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        iv_mic1 = findViewById(R.id.iv_mic);
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text);
        recyclerView = findViewById(R.id.recyclerView);




        iv_mic1.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);


            }
            catch (Exception e) {
                Toast.makeText(MainActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        DB = new  DBHelper(MainActivity.this);
        product_id = new ArrayList<>();
        name_Product = new ArrayList<>();
        price_Product = new ArrayList<>();
        quantity_Product = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(this,MainActivity.this, product_id, name_Product, price_Product,quantity_Product);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



    }



    private void storeDataInArrays() {

        Cursor cursor = DB.readAllData();
        if(cursor.getCount() == 0){
            //empty_imageview.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, " move it" , Toast.LENGTH_SHORT).show();
            //no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){

                product_id.add(cursor.getString(0));
                name_Product.add(cursor.getString(1));
                price_Product.add(cursor.getString(2));
                quantity_Product.add(cursor.getString(3));
            }
            // empty_imageview.setVisibility(View.GONE);
            // no_data.setVisibility(View.GONE);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case 0:{

                if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
                    if (resultCode == RESULT_OK && data != null) {
                        result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        mAnswer = result.get(0);
                        String[] speechWords = mAnswer.split("\\s+");

                        String item = null;
                        int quantity = 0;
                        for (String word : speechWords) {
                            if (word.matches("\\d+")) {
                                quantity = Integer.parseInt(word);
                            } else {item = word;}
                        }

                        if (item != null && quantity > 0 ) {
                            //onResultExecute(item,quantity,num);*/
                            insertData(item, quantity);
                            /*storeDataInArrays();*/
                        } else {
                            Toast.makeText(MainActivity.this, "Error: Could not generate invoice", Toast.LENGTH_SHORT).show();
                            // show an error message or prompt the user to try again
                        }
                        tv_Speech_to_text.setText(mAnswer);
                        //getListView(result);
                        // onResultExecute(result.get(0),num);
                    }

                }
                recreate();

            }


            case 1:{

                if(requestCode == 1){
                    recreate();
                }

            }
        }

    }


    public void insertData(String item, int quantity) {
        Boolean checkuserpass1 = DB.checkandupdate(item, quantity);
        if (checkuserpass1) {
            Toast.makeText(MainActivity.this, "Updated successful", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }



   /* protected void onResultExecute(String string,int po){

        for(int i=po;i<=po;i++) ll.add(i, string);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ll);
        recyclerView.setAdapter(adapter);
        num++;

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu_option,menu);
        MenuItem searchViewItem = menu.findItem(R.id.item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item01: {
                Toast.makeText(getApplicationContext(),"Add Product",Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this, AddActivity.class);
                startActivity(in);
                break;
            }

            case R.id.item02: {
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.item03:{
                Toast.makeText(getApplicationContext(),"Exit",Toast.LENGTH_SHORT).show();
                finish();;
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}