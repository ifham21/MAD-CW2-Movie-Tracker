package com.example.movieTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;

public class DataBase extends SQLiteOpenHelper {

//To create Database, I used the knowledge I gained from the Tutorial 5 and the references are as follows
/*
https://developer.android.com/training/data-storage/sqlite#java
https://dzone.com/articles/create-a-database-android-application-in-android-s
https://www.tutorialspoint.com/android/android_sqlite_database.htm
*/


    private static final String DATABASE_NAME="movieDatabase.db";
    private static final String TABLE_NAME="Register_table";

    public static final String movie = "NAME";
    public static final String year = "YEAR";
    public static final String dir = "DIRECTOR";
    public static final String act = "ACTORS";
    public static final String rate = "RATING";
    public static final String review = "REVIEW";
    public static final String inputString_Col = "Availability";


    public DataBase (Context context){
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+ TABLE_NAME +"(NAME Text PRIMARY KEY , YEAR Number, DIRECTOR Text, ACTORS Text, RATING Number, REVIEW Text,favourites Text, Availability Text )");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME );
           onCreate(db);
    }

    public boolean insertData(String name, String year, String director, String actors, String rating,String review){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(movie,name);
        contentValues.put(DataBase.year,year);
        contentValues.put(dir,director);
        contentValues.put(act,actors);
        contentValues.put(rate,rating);
        contentValues.put(DataBase.review,review);


        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;

    }

    public Cursor displayMovie(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM "+DataBase.TABLE_NAME+ " ORDER BY "+DataBase.movie +" ASC" , new String[] {} );
        System.out.println(Arrays.toString(result.getColumnNames()));
        return result;
    }


    public Cursor displayFavorites(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from  "+TABLE_NAME+" WHERE "+ inputString_Col +"= 'true'",null);
        return result;
    }


    public void addFavoritesList(String NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        String test = "UPDATE "+TABLE_NAME+" SET "+ inputString_Col +" = 'true' WHERE NAME = '"+NAME+"'";
        System.out.println(test);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+ inputString_Col +" = 'true' WHERE NAME = '"+NAME+"'");


    }


    public void RemoveFavoritesList(String NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        String test = "UPDATE "+TABLE_NAME+" SET "+ inputString_Col +" = 'false' WHERE NAME = '"+NAME+"'";
        System.out.println(test);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+ inputString_Col +" = 'false' WHERE NAME = '"+NAME+"'");


    }

    public boolean updateEditData(String name, String year, String director, String actors, String rating, String review, String availability) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(movie,name);
        contentValues.put(DataBase.year,year);
        contentValues.put(dir,director);
        contentValues.put(act,actors);
        contentValues.put(rate,rating);
        contentValues.put(DataBase.review, review);
        contentValues.put(inputString_Col,availability);
        db.update(TABLE_NAME, contentValues, "NAME = ?",new String[] { name });

        return true;


    }

    //https://www.journaldev.com/12478/android-searchview-example-tutorial
    //https://stackoverflow.com/questions/18868712/android-sqlite-search-by-name
    public Cursor search(String letters){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE NAME OR DIRECTOR LIKE " +
                "'"+letters+"%' OR NAME LIKE '%"+letters+"%'  OR NAME LIKE '%"+letters+"'",null);
        return cursor;

    }


}
