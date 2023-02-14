package com.example.demo1;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class View_List_Content extends AppCompatActivity {

    DBHelper DB;
    ArrayList<User> userlist;
    ListView listView;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents);

        DB = new DBHelper(this);
        userlist = new ArrayList<>();
        Cursor data = DB.readAllData();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(View_List_Content.this, "there is nothing in database", Toast.LENGTH_SHORT).show();
        }
        else {

            while (data.moveToNext()){
                user = new User(data.getString(1),data.getString(2));
                userlist.add(user);
            }
            TwoColumn_ListAdapter adapter = new TwoColumn_ListAdapter(this,R.layout.list_adapter_view,userlist);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }
}
