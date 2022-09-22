package com.example.libraryapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LibraryDao {
    @Insert
    void insertin(AccountTable accountTable);

    @Query("SELECT * FROM `Account Holders`")
    List<AccountTable> getAll();

    @Query("SELECT COUNT(*) FROM `Account Holders`")
    int count();

    @Query("SELECT * FROM `Account Holders` WHERE ID=Username")
    List<AccountTable> getUsers();


    @Delete
    void delete(AccountTable accountTable);
}
