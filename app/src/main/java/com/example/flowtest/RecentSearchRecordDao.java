package com.example.flowtest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecentSearchRecordDao {

    @Insert
    void insertRecentSearchRecord(RecentSearchRecord recentSearchRecord);
    @Query("SELECT * FROM recent_search_record ORDER BY id DESC LIMIT :limit")
    List<RecentSearchRecord> getRecentSearchRecordsList(int limit);

}