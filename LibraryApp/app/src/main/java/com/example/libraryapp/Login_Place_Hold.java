package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login_Place_Hold extends AppCompatActivity implements View.OnClickListener {

    private LibraryDatabase database;
    List<AccountTable> GetUsers;
    List<PlaceHoldTable> GetBooks5;
    List<TransactionTable> GetMessages;
    int id;
    boolean check = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_place_hold);

        View UserDoLogin = findViewById(R.id.UserDoLogin);
        UserDoLogin.setOnClickListener(this);
        View backToMain = findViewById(R.id.backToMain);
        backToMain.setOnClickListener(this);

        database=LibraryDatabase.getInstance(this);
        GetUsers = database.AccountDao().getAll();
        GetBooks5 = database.placeHoldDao().getAll();
        GetMessages = database.transactionDao().getAll();
    }

    @Override
    public void onClick(View view) {
        EditText UserLoginUserName;
        EditText UserLoginPassword;
        String UserLoginUsernameInput;
        String UserLoginPasswordInput;
        TextView dispusername;
        TextView dispbookhold;
        TextView dispreservation;
        String passedString = "";
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            passedString = extras.getString("Title");
        }
        String text = passedString;
        int usernameCount = GetUsers.size();
        int booksCount = GetBooks5.size();
        int messagesCount = GetMessages.size();
        if (view.getId() == R.id.UserDoLogin) {
            Log.d("MainActivity", "Button Clicked");
            UserLoginUserName = findViewById(R.id.UserLoginUsername);
            UserLoginUsernameInput = UserLoginUserName.getText().toString();
            UserLoginPassword = findViewById(R.id.UserLoginPassword);
            UserLoginPasswordInput = UserLoginPassword.getText().toString();

            for (int i = 0; i < usernameCount; i++) {
                if (GetUsers.get(i).getUsername().equals(UserLoginUsernameInput)&&(GetUsers.get(i).getPassword().equals(UserLoginPasswordInput))) {
                    String Error2 = "Logged In";
                    Toast.makeText(Login_Place_Hold.this, Error2, Toast.LENGTH_LONG).show();
                    dispusername = findViewById(R.id.dispusername);
                    dispbookhold = findViewById(R.id.dispbook);
                    dispusername.setText("Customer Username: " + UserLoginUsernameInput);
                    dispbookhold.setText("Book Title: " + text);

                    id = GetMessages.get(messagesCount-1).getId();
                    int newID = id+1;
                    dispreservation = findViewById(R.id.dispreservationnum);
                    dispreservation.setText("Reservation Number "+(Integer.toString(newID)));

                    String msg = "Transaction Type = PlaceHold, "+UserLoginUsernameInput+", "+Integer.toString(newID);
                    TransactionTable newtransactionTable= new TransactionTable(msg);
                    database.transactionDao().insert(newtransactionTable);

                    database.placeHoldDao().deleteByTitle(text);

                    check = true;
                }
                else
                {
                    check=false;
                }
            }
            if (UserLoginUsernameInput.equals("") || UserLoginPasswordInput.equals("")) {
                String BlankText = "Username/Password can not be Left Blank";
                Toast.makeText(Login_Place_Hold.this, BlankText, Toast.LENGTH_SHORT).show();
            }
        }
        if(view.getId()==R.id.backToMain)
        {
            Intent main = new Intent(Login_Place_Hold.this,MainActivity.class);
            startActivity(main);
        }
        }

    }