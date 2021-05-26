package com.example.movieTracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMovie extends AppCompatActivity {

    private DataBase mydb;

    ArrayList<String> selectedItems = new ArrayList<>();
    ListView listView;

    EditText  year, director, actor, rate, review;
    Button reg_sub;
    TextView movie,availability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        mydb = new DataBase(this);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        Toast.makeText(EditMovie.this, name, Toast.LENGTH_LONG).show();

        movie = (TextView) findViewById(R.id.movie_txtEdit);
        year = (EditText) findViewById(R.id.year_txt);
        director = (EditText) findViewById(R.id.dir_txt);
        actor = (EditText) findViewById(R.id.act_txt);
        review =(EditText) findViewById(R.id.rev_txt);
        rate = (EditText) findViewById(R.id.rate_txt);
        availability = (TextView) findViewById(R.id.favorite_txt);

        reg_sub = (Button) findViewById(R.id.submit_reg_btn);
        fillFields(name);

    }


    public void fillFields(String name){
        Cursor res = mydb.search(name);

        if (res.getCount() == 0) {
            Toast.makeText(EditMovie.this, "Nothing to show", Toast.LENGTH_LONG).show();
        } else {
            while (res.moveToNext()) {
                movie.append(res.getString(0));
                year.append(res.getString(1));
                director.append(res.getString(2));
                actor.append(res.getString(3));
                rate.append(res.getString(4));
                review.append(res.getString(5));
                availability.append(res.getString(7));
                if(res.getString(7).equals("true")) {
                    availability.setText("Favorite");
                }else{
                    availability.setText("Not Favorite");
                }

            }
        }
    }

     public void onClick(View v){
         boolean isUpdate = mydb.updateEditData(
                 movie.getText().toString(),
                 year.getText().toString(),
                 director.getText().toString(),
                 actor.getText().toString(),
                 rate.getText().toString(),
                 review.getText().toString(),
                 availability.getText().toString()
         );

         if (isUpdate == true) {
             Toast.makeText(EditMovie.this, "Data Updated", Toast.LENGTH_LONG).show();
             finish();

         } else {
             Toast.makeText(EditMovie.this, "Data Is Not Updated", Toast.LENGTH_LONG).show();

         }
     }
    @Override
    public void finish() {
        super.finish();
    }

}
