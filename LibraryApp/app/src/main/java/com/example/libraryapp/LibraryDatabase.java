package com.example.libraryapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AccountTable.class,PlaceHoldTable.class,TransactionTable.class}, version=26, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {
    private static LibraryDatabase database;
    private static String DATABASE_NAME = "Database";
    public abstract LibraryDao AccountDao();
    public abstract PlaceHoldDao placeHoldDao();
    public abstract TransactionDao transactionDao();

    public static synchronized LibraryDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    LibraryDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }

    public void populateInitialData() {
        if (AccountDao().count() == 0) {
            runInTransaction(() -> {
                AccountDao().insertin(
                        new AccountTable("anton", "t3nn1sch@mp22"));
                AccountDao().insertin(
                        new AccountTable("bernie", "k3ralaf@n"));
                AccountDao().insertin(
                        new AccountTable("shirleybee", "carmel2chicago"));
            });
        }

        if (placeHoldDao().count() == 0) {
            runInTransaction(() -> {
                placeHoldDao().insert(
                        new PlaceHoldTable("Angela’s Ashes", "Frank McCourt","Memoir"));
                placeHoldDao().insert(
                        new PlaceHoldTable("The IDA Pro Book", "Chris Eagle","Computer Science"));
                placeHoldDao().insert(
                        new PlaceHoldTable("Frankenstein", "Mary Shelley","Fiction"));
            });
        }
    }
}
