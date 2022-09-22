package com.example.libraryapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transactions")
public class TransactionTable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Message")
    private String message;

    // constructor without id
    public TransactionTable(String message) {
        this.message = message;
    }
    // getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String toString()
    {
        return message;
    }
}
