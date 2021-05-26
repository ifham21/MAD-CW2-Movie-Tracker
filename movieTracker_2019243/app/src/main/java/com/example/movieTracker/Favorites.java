package com.example.movieTracker;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {
    ArrayList<String> checkedItems1 = new ArrayList<>();

    ListView availability;
    Button saveBtn;
    CheckedTextView checked;
    DataBase mydb;
    DisplayMovie input=new DisplayMovie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        //checked.setChecked(true);
       // checked.isChecked();
        availability = (ListView) findViewById(R.id.AddFavouriteBtn);
        saveBtn = (Button) findViewById(R.id.buttonSave);
        availability.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mydb = new DataBase(this);
        populateListView();
    }

    public void populateListView(){
        Cursor data = mydb.displayFavorites();
        ArrayList<String> listData1 = new ArrayList<>();
        while (data.moveToNext()) {
            listData1.add(data.getString(0));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.checkbox, R.id.txt_Availability, listData1);
        availability.setAdapter(adapter);
        for(int x=0; x<listData1.size(); x++)
            availability.setItemChecked(x, true);
            availability.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (checkedItems1.contains(selectedItem))
                    checkedItems1.remove(selectedItem);
                else
                    checkedItems1.add(selectedItem);
            }
        });
    }

    public void saveMovie(View view){

        String checkItems = "";
        for (String item : checkedItems1) {
            mydb.RemoveFavoritesList(item);
            if (checkItems == "") {
                checkItems = item;
            }
            else
                checkItems += "/" + item;
        }
        Toast.makeText(this, checkItems+" "+"removed", Toast.LENGTH_LONG).show();
    }


}
