package com.example.demo1;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ProductData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Productdetail";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Pro_Nane = "PRODUCT";
    private static final String COLUMN_Pro_Price = "PRICE";
   //private static final String COLUMN_Pro_QTY = "QTY";

    DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Pro_Nane + " TEXT, " +
                COLUMN_Pro_Price + " INTEGER); " ;
        DB.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);
    }

    public Boolean insertproductdata(String product, String price) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_Pro_Nane, product);
        contentValues.put(COLUMN_Pro_Price, price);

        long result = DB.insert(" Productdetail", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    //adding data
    void addProduct(String pro_name, Integer pro_price){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_Pro_Nane, pro_name);
        cv.put(COLUMN_Pro_Price, pro_price);

        long result = DB.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //read data in table
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = null;
        if(DB != null){
            cursor = DB.rawQuery(query, null);
        }
        return cursor;
    }


    //update row
    void updateData(String row_id, String pro_name, String pro_price){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_Pro_Nane, pro_name);
        cv.put(COLUMN_Pro_Price, pro_price);


        long result = DB.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //delete row
    void deleteOneRow(String row_id){
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    // delate all data
    void deleteAllData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("DELETE FROM " + TABLE_NAME);
    }



}
