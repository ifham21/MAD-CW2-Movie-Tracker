package com.example.movieTracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterMovie extends AppCompatActivity {
    DataBase mydb;
    EditText movie_name, year, director, actor,rate,review, checkbox;
    Button submit_register;
//https://dzone.com/articles/create-a-database-android-application-in-android-s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mydb = new DataBase(this);


        movie_name = (EditText) findViewById(R.id.movie_txt);
        year = (EditText) findViewById(R.id.year_txt);
        director = (EditText) findViewById(R.id.dir_txt);
        actor = (EditText) findViewById(R.id.act_txt);
        rate = (EditText) findViewById(R.id.rate_txt);
        review = (EditText) findViewById(R.id.rev_txt);

        submit_register = (Button) findViewById(R.id.submit_reg_btn);

    }


   public void onClick(View view) {
    String movieN = movie_name.getText().toString();
    String yearT = year.getText().toString();
    String dir = director.getText().toString();
    String act = actor.getText().toString();
    String rateT = rate.getText().toString();
    String rev = review.getText().toString();

        boolean bool = mydb.insertData(movieN, yearT, dir, act,rateT,rev);

        if (bool == true) {
            Toast.makeText(RegisterMovie.this, "Data Inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RegisterMovie.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }
        movie_name.setText("");
        year.setText("");
        director.setText("");
        actor.setText("");
        rate.setText("");
        review.setText("");

    }



}
