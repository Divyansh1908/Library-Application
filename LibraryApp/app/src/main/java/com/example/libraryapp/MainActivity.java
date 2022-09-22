package com.example.libraryapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    List<AccountTable> dataTables = new ArrayList<AccountTable>();
    private LibraryDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View createAcc = findViewById(R.id.createAcc);
        createAcc.setOnClickListener(this);

        View placeHold = findViewById(R.id.placeHold);
        placeHold.setOnClickListener(this);

        View manageSystem = findViewById(R.id.manageSystem);
        manageSystem.setOnClickListener(this);

        database=LibraryDatabase.getInstance(this);
        database.populateInitialData();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.createAcc)
        {
            Intent create_acc = new Intent(this,Create_Account.class);
            startActivity(create_acc);
        }
        else if(view.getId()==R.id.placeHold)
        {
            Intent place_hold = new Intent(this,Place_Hold.class);
            startActivity(place_hold);
        }
        else if(view.getId()==R.id.manageSystem)
        {
            Intent manage_system = new Intent(this, Manage_System.class);
            startActivity(manage_system);
        }
        else
        {
            finish();
        }
    }
}