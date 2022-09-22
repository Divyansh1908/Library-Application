package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Computer_Science extends AppCompatActivity implements View.OnClickListener{

    private LibraryDatabase database;
    List<PlaceHoldTable> GetBooks1;
    List<String> cs=new ArrayList<>();
    private ArrayAdapter<PlaceHoldTable> ComputerScienceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science);

        ListView ListBox1 = findViewById(R.id.csbooks);
        View Exit = findViewById(R.id.Exit2);
        Exit.setOnClickListener(this);

        database=LibraryDatabase.getInstance(this);
        GetBooks1 = database.placeHoldDao().getAll();
        boolean b = GetBooks1.isEmpty();

        for(int i=0;i<GetBooks1.size();i++)
        {
            if(GetBooks1.get(i).getGenre().equals("Computer Science")) {
                String csBooks = GetBooks1.get(i).getTitle();
                cs.add(csBooks);
            }
        }

        ComputerScienceAdapter = new ArrayAdapter(this,R.layout.computerbooks,R.id.computerbook,cs);
        ListBox1.setAdapter(ComputerScienceAdapter);
        ListBox1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent Login = new Intent(Computer_Science.this,Login_Place_Hold.class);
                Bundle extraInfo = new Bundle();
                extraInfo.putString("Title",selectedItem);
                Login.putExtras(extraInfo);
                startActivity(Login);
            }
        });

        if(cs.isEmpty()==true)
        {
            Toast.makeText(Computer_Science.this, "No books available for this Genre, Please select the Exit button", Toast.LENGTH_LONG).show();
        }
        else
        {
            Log.d("MainActivity", "Book is there");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.Exit2)
        {
            Intent fin = new Intent(this,MainActivity.class);
            startActivity(fin);
        }
    }
}