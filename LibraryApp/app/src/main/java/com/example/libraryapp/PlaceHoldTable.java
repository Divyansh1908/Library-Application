package com.example.libraryapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Book Collection")
public class PlaceHoldTable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "Book_Title")
    private String Title;

    @ColumnInfo(name = "Author")
    private String Author;

    @ColumnInfo(name = "Genre")
    private String Genre;


    // constructor without id
    public PlaceHoldTable(String Title, String Author, String Genre) {
        this.Title = Title;
        this.Author = Author;
        this.Genre = Genre;
    }
    // getters/setters
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
