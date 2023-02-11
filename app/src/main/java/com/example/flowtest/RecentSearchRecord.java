package com.example.flowtest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recent_search_record")
public class RecentSearchRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "search_term")
    private String searchTerm;

    public RecentSearchRecord(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}