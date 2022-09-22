package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ManagementInsider extends AppCompatActivity implements View.OnClickListener{
    private LibraryDatabase database1;
    List<TransactionTable> GetMessages;
    private ArrayAdapter<TransactionTable> transactionTableArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_insider);

        View ok = findViewById(R.id.IfOk);
        ok.setOnClickListener(this);

        ListView ListBox = findViewById(R.id.Messages_List);
        database1 = LibraryDatabase.getInstance(this);
        GetMessages = database1.transactionDao().getAll();
        transactionTableArrayAdapter = new ArrayAdapter<>(this,R.layout.messages,R.id.messages1,GetMessages);
        ListBox.setAdapter(transactionTableArrayAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.IfOk)
        {
            Intent WantToAdd = new Intent(this,WantToAdd.class);
            startActivity(WantToAdd);
        }
    }
}