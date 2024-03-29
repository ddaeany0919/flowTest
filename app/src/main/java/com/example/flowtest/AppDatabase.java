package com.example.flowtest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RecentSearchRecord.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract RecentSearchRecordDao recentSearchRecordDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}