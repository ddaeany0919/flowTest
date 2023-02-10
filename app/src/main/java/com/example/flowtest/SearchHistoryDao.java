package com.example.flowtest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SearchHistoryDao {
    @Insert
    void insert(SearchHistory searchHistory);

    @Query("SELECT * FROM search_history ORDER BY id DESC LIMIT 10")
    LiveData<List<SearchHistory>> getAllSearchHistory();
}