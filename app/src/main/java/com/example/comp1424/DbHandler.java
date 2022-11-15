package com.example.comp1424;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_DATE = "date";
    private static final String KEY_LENGTH = "length";
    private static final String KEY_OBS = "observation";

    private static final String TABLE_Users1 = "userobservation";

    private static final String KEY_ID1 = "id1";
    private static final String KEY_ANIMAL = "animal";
    private static final String KEY_VEG = "vegetation";
    private static final String KEY_WETH = "weather";
    private static final String KEY_TRAIL = "trails";
    private static final String KEY_TIME = "time";
    private static final String KEY_COM = "comment";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_LENGTH + " TEXT,"
                + KEY_OBS + " TEXT"+ ")";
        String CREATE_TABLE1 = "CREATE TABLE " + TABLE_Users1 + "("
                + KEY_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ANIMAL + " TEXT,"
                + KEY_VEG + " TEXT,"
                + KEY_WETH + " TEXT,"
                + KEY_TRAIL + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_COM + " TEXT"+ ")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users1);

        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails(String name, String location, String date,String length,String observation ){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_LOC, location);
        cValues.put(KEY_DATE, date);
        cValues.put(KEY_LENGTH,length);
        cValues.put(KEY_OBS,observation);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users1,null, cValues);

        db.close();
    }
    void insertUserDetails1(String animal,String vegetation,String weather,String trails,String time,String comment ){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();

        cValues.put(KEY_ANIMAL, animal);
        cValues.put(KEY_VEG, vegetation);
        cValues.put(KEY_WETH, weather);
        cValues.put(KEY_TRAIL,trails);
        cValues.put(KEY_TIME,time);
        cValues.put(KEY_COM,comment);
        // Insert the new row, returning the primary key value of the new row
        long newRowId1 = db.insert(TABLE_Users1,null, cValues);
        db.close();
    }


    // Get User Details
    public ArrayList  <HashMap<String, String>>
    GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList <HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, date,length,observation FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            user.put("location",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            user.put("date",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            user.put("length",cursor.getString(cursor.getColumnIndex(KEY_LENGTH)));
            user.put("observation",cursor.getString(cursor.getColumnIndex(KEY_OBS)));
            userList.add(user);
        }
        return  userList;
    }

    public ArrayList  <HashMap<String, String>>
    GetUsers1() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList1 = new ArrayList<>();
        String query = "SELECT animal, vegetation, weather,trails,time,comment FROM " + TABLE_Users1;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user1 = new HashMap<>();
            user1.put("animal", cursor.getString(cursor.getColumnIndex(KEY_ANIMAL)));
            user1.put("vegetation", cursor.getString(cursor.getColumnIndex(KEY_VEG)));
            user1.put("weather", cursor.getString(cursor.getColumnIndex(KEY_WETH)));
            user1.put("trails", cursor.getString(cursor.getColumnIndex(KEY_TRAIL)));
            user1.put("time", cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            user1.put("comment", cursor.getString(cursor.getColumnIndex(KEY_COM)));
            userList1.add(user1);
        }
        return userList1;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, date, length,observation FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_LOC, KEY_DATE,KEY_LENGTH,KEY_OBS}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            user.put("location",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            user.put("length",cursor.getString(cursor.getColumnIndex(KEY_LENGTH)));
            user.put("observation",cursor.getString(cursor.getColumnIndex(KEY_OBS)));
            userList.add(user);
        }
        return  userList;
    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String location, String designation, int id,String date,String length,String observation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_LOC, location);
        cVals.put(KEY_DATE, date);
        cVals.put(KEY_LENGTH,length);
        cVals.put(KEY_OBS,observation);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }



}