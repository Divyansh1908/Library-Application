package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Place_Hold extends AppCompatActivity implements View.OnClickListener{

    private LibraryDatabase database;
    List<PlaceHoldTable> GetBooks;
    List<String> Genres=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_hold);

        View Computer = findViewById(R.id.Computer);
        Computer.setOnClickListener(this);
        View Fiction = findViewById(R.id.Fiction);
        Fiction.setOnClickListener(this);
        View Memoir = findViewById(R.id.Memoir);
        Memoir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Computer)
        {
            Intent computer = new Intent(this,Computer_Science.class);
            startActivity(computer);
        }
        if(view.getId()==R.id.Fiction)
        {
            Intent fiction = new Intent(this,Fiction.class);
            startActivity(fiction);
        }
        if(view.getId()==R.id.Memoir)
        {
            Intent memoir = new Intent(this,Memoir.class);
            startActivity(memoir);
        }

    }
}