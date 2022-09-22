package com.example.libraryapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlaceHoldDao {
    @Insert
    void insert(PlaceHoldTable placeHoldTable);

    @Query("SELECT * FROM `Book Collection`")
    List<PlaceHoldTable> getAll();

    @Query("SELECT COUNT(*) FROM `Book Collection`")
    int count();

    @Query("SELECT * FROM `Book Collection` WHERE ID=Genre")
    List<PlaceHoldTable> getGenre();

    @Query("delete from `Book Collection` where Book_Title = :taskString")
    void deleteByTitle(String taskString);

    @Delete
    void delete(PlaceHoldTable placeHoldTable);
}
