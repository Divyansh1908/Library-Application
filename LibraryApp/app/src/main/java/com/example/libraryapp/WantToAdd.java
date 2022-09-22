package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WantToAdd extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_add);
        View yes = findViewById(R.id.IfYes);
        yes.setOnClickListener(this);
        View no = findViewById(R.id.IfNo);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.IfNo)
        {
            Intent backHome = new Intent(this,MainActivity.class);
            startActivity(backHome);
        }
        if(view.getId()==R.id.IfYes)
        {
            Intent addBook = new Intent(this,AddBook.class);
            startActivity(addBook);
        }
    }
}