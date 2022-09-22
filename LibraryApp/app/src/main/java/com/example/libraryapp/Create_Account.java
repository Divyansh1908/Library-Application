package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Create_Account extends AppCompatActivity implements View.OnClickListener {

    private LibraryDatabase database;
    List<AccountTable> GetUsers;
    List<TransactionTable> transactMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        View Submit = findViewById(R.id.EnterDetails);
        Submit.setOnClickListener(this);
        database=LibraryDatabase.getInstance(this);
        GetUsers = database.AccountDao().getAll();
        transactMessage = database.transactionDao().getAll();
    }

    @Override
    public void onClick(View view) {
        EditText UserName;
        EditText Password;
        String UsernameInput;
        String PasswordInput;
        boolean check = true;
        int usernameCount = GetUsers.size();
        if (view.getId() == R.id.EnterDetails) {
            Log.d("MainActivity", "Button Clicked");
            UserName = findViewById(R.id.UserName);
            UsernameInput = UserName.getText().toString();
            Password = findViewById(R.id.Password);
            PasswordInput = Password.getText().toString();
            for (int i = 0; i < usernameCount; i++) {
                if (GetUsers.get(i).getUsername().equals(UsernameInput)) {
                    String Error2 = "This username already Exists";
                    Toast.makeText(Create_Account.this, Error2, Toast.LENGTH_SHORT).show();
                    check = false;
                }
            }
            if (UsernameInput.equals("") || PasswordInput.equals("")) {
                String BlankText = "Username/Password can not be Left Blank";
                Toast.makeText(Create_Account.this, BlankText, Toast.LENGTH_LONG).show();

            } else if (UsernameInput.equals("!admin2")) {
                String Error1 = "This username is reserved for librarian";
                Toast.makeText(Create_Account.this, Error1, Toast.LENGTH_LONG).show();
            }

            else if (check==true) {
                String success = "Account Created";
                Toast.makeText(Create_Account.this, success, Toast.LENGTH_SHORT).show();
                AccountTable newAccount = new AccountTable(UsernameInput, PasswordInput);
                database.AccountDao().insertin(newAccount);
                String msg = "New Account, Username:- "+UsernameInput;
                TransactionTable newtransactionTable= new TransactionTable(msg);
                database.transactionDao().insert(newtransactionTable);
                finish();
            }
            else if(check==false)
            {
                finish();
            }
            else
            {
                Log.d("MainActivity", UsernameInput);
                Log.d("MainActivity", PasswordInput);
            }
        }
    }
}