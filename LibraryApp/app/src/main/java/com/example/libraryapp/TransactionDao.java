package com.example.libraryapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(TransactionTable transactionTable);

    @Query("SELECT * FROM `Transactions`")
    List<TransactionTable> getAll();

    @Query("SELECT COUNT(*) FROM `Transactions`")
    int count();

    @Query("SELECT * FROM `Transactions` WHERE ID=message")
    List<TransactionTable> getMessage();

    @Delete
    void delete(TransactionTable transactionTable);
}
