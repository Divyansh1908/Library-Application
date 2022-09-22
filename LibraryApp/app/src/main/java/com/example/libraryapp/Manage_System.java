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

public class Manage_System extends AppCompatActivity implements View.OnClickListener {

    private LibraryDatabase database;
    List<AccountTable> GetUsers;
    List<TransactionTable> GetMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        View DoLogin = findViewById(R.id.DoLogin);
        DoLogin.setOnClickListener(this);

        database=LibraryDatabase.getInstance(this);
        GetUsers = database.AccountDao().getAll();
        GetMessages = database.transactionDao().getAll();
    }

    @Override
    public void onClick(View view) {
        EditText UserName;
        EditText Password;
        String LoginUsernameInput;
        String LoginPasswordInput;
        boolean check = true;
        int usernameCount = GetUsers.size();
        if (view.getId() == R.id.DoLogin) {
            Log.d("MainActivity", "Button Clicked");
            UserName = findViewById(R.id.LoginUsername);
            LoginUsernameInput = UserName.getText().toString();
            Password = findViewById(R.id.LoginPassword);
            LoginPasswordInput = Password.getText().toString();

            if (LoginUsernameInput.equals("!admin2") && LoginPasswordInput.equals("!admin2")) {
                String SuccessfulLogin = "Login Successful";
                Toast.makeText(Manage_System.this, SuccessfulLogin, Toast.LENGTH_SHORT).show();
                Intent managementInsider = new Intent(this, ManagementInsider.class);
                startActivity(managementInsider);
            }
            else
            {
                String FailText = "Login Failed ";
                Toast.makeText(Manage_System.this, FailText, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
