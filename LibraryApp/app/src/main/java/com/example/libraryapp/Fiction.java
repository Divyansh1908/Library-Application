package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Fiction extends AppCompatActivity implements View.OnClickListener{

    private LibraryDatabase database;
    List<PlaceHoldTable> GetBooks2;
    List<String> fiction = new ArrayList<>();
    private ArrayAdapter<PlaceHoldTable> FictionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiction);

        View Exit = findViewById(R.id.Exit);
        Exit.setOnClickListener(this);

        ListView ListBox2 = findViewById(R.id.fictionbooks);

        database = LibraryDatabase.getInstance(this);
        GetBooks2 = database.placeHoldDao().getAll();

        for (int i = 0; i < GetBooks2.size(); i++) {
            if (GetBooks2.get(i).getGenre().equals("Fiction")) {
                String fictBooks = GetBooks2.get(i).getTitle();
                fiction.add(fictBooks);
            }
        }

        FictionAdapter = new ArrayAdapter(this, R.layout.fictionbooks, R.id.fictionbook, fiction);
        ListBox2.setAdapter(FictionAdapter);
        ListBox2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                System.out.println("Item is " + selectedItem);
                Intent Login = new Intent(Fiction.this,Login_Place_Hold.class);
                Bundle extraInfo = new Bundle();
                extraInfo.putString("Title",selectedItem);
                Login.putExtras(extraInfo);
                startActivity(Login);
            }
        });

        if(fiction.isEmpty()==true)
        {
            Toast.makeText(Fiction.this, "No books available for this Genre, Please select the Exit button", Toast.LENGTH_LONG).show();
        }
        else
        {
            Log.d("MainActivity", "Book is there");
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.Exit)
        {
            Intent fin = new Intent(this,MainActivity.class);
            startActivity(fin);
        }

    }
}
