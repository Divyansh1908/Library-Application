package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddBook extends AppCompatActivity implements View.OnClickListener{

    private LibraryDatabase database;
    List<PlaceHoldTable> GetBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        View AddBook = findViewById(R.id.AddBook);
        AddBook.setOnClickListener(this);
        database=LibraryDatabase.getInstance(this);
        GetBooks = database.placeHoldDao().getAll();
    }

    @Override
    public void onClick(View view) {
        EditText Title;
        EditText Author;
        EditText Genre;
        String TitleInput;
        String AuthorInput;
        String GenreInput;
        boolean check = true;
        int BooksCount = GetBooks.size();
        if (view.getId() == R.id.AddBook) {
            Log.d("MainActivity", "Button Clicked");
            Title = findViewById(R.id.BookTitle);
            TitleInput = Title.getText().toString();
            Author = findViewById(R.id.Author);
            AuthorInput = Author.getText().toString();
            Genre = findViewById(R.id.Genre);
            GenreInput = Genre.getText().toString();
            System.out.println(BooksCount);

            for (int i = 0; i < BooksCount; i++) {
                if (GetBooks.get(i).getTitle().equals(TitleInput)) {
                    String Error3 = "This Book already Exists";
                    Toast.makeText(AddBook.this, Error3, Toast.LENGTH_LONG).show();
                    check = false;
                }
            }
            if (TitleInput.equals("") || AuthorInput.equals("")|| GenreInput.equals("")) {
                String BlankText1 = "None of the data can be left blank";
                Toast.makeText(AddBook.this, BlankText1, Toast.LENGTH_LONG).show();
            }
            else if (check==true) {
                String success1 = "Book Added";
                Toast.makeText(AddBook.this, success1, Toast.LENGTH_SHORT).show();
                PlaceHoldTable newplaceHoldTable = new PlaceHoldTable(TitleInput, AuthorInput, GenreInput);
                database.placeHoldDao().insert(newplaceHoldTable);
                Intent main = new Intent(AddBook.this,MainActivity.class);
                startActivity(main);
            }
            else if(check==false)
            {
                Intent main1 = new Intent(AddBook.this,MainActivity.class);
                startActivity(main1);
            }
            else
            {
                Log.d("MainActivity", TitleInput);
                Log.d("MainActivity", TitleInput);
            }
        }
    }
}