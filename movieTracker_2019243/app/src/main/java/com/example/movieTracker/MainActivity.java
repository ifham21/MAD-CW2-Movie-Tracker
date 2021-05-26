package com.example.movieTracker;
//this activity content connecting function activities to the buttons

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

////https://developer.android.com/guide/navigation/navigation-getting-started

    private Button registerProduct;
    private Button displayProduct;
    private Button availability;
    private Button editProduct;
    private Button search;
    private Button recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerProduct = (Button) findViewById(R.id.reg_btn);
        displayProduct = (Button) findViewById(R.id.display_btn);
        availability = (Button) findViewById(R.id.fav_btn);
        editProduct = (Button) findViewById(R.id.edit_btn);
        search = (Button) findViewById(R.id.search_btn);
        recipe = (Button) findViewById(R.id.Rating_btn);


        registerProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, RegisterMovie.class));

            }
        });

        displayProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DisplayMovie.class);
                startActivity(i);
            }
        });

        availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Favorites.class));
            }
        });

        editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditMain.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });

        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Rating.class));
            }
        });


    }
}
