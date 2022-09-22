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

public class Memoir extends AppCompatActivity implements View.OnClickListener {

    private LibraryDatabase database;
    List<PlaceHoldTable> GetBooks3;
    List<String> memoir = new ArrayList<>();
    private ArrayAdapter<PlaceHoldTable> MemoirAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoir);
        View Exit = findViewById(R.id.Exit1);
        Exit.setOnClickListener(this);

        ListView ListBox3 = findViewById(R.id.Memoirbooks);
        database = LibraryDatabase.getInstance(this);
        GetBooks3 = database.placeHoldDao().getAll();
        memoir.clear();

        for (int i = 0; i < GetBooks3.size(); i++) {
            if (GetBooks3.get(i).getGenre().equals("Memoir")) {
                String memoirBooks = GetBooks3.get(i).getTitle();
                memoir.add(memoirBooks);
            }
        }

        MemoirAdapter = new ArrayAdapter(this, R.layout.memoirbooks, R.id.memoirbook, memoir);
        ListBox3.setAdapter(MemoirAdapter);
        ListBox3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                System.out.println("Item is " + selectedItem);
                Intent Login = new Intent(Memoir.this,Login_Place_Hold.class);
                Bundle extraInfo = new Bundle();
                extraInfo.putString("Title",selectedItem);
                Login.putExtras(extraInfo);
                startActivity(Login);
            }
        });

        if(memoir.isEmpty()==true)
        {
            Toast.makeText(Memoir.this, "No books available for this Genre, Please select the Exit button", Toast.LENGTH_LONG).show();
        }
        else
        {
            Log.d("MainActivity", "Book is there");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.Exit1)
        {
            Intent fin = new Intent(this,MainActivity.class);
            startActivity(fin);
        }
    }
}