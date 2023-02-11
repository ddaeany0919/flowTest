package com.example.flowtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecentSearchRecordActivity extends AppCompatActivity {

    private RecyclerView mRecentSearchRecordRecyclerView;
    private RecentSearchRecordAdapter mRecentSearchRecordAdapter;
    private final List<RecentSearchRecord> mRecentSearchRecordList = new ArrayList<>();
    private RecentSearchRecordRepository mRecentSearchRecordRepository;
    private final int mRecentSearchLimit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_search_record);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mRecentSearchRecordRecyclerView = findViewById(R.id.recent_search_record_recycler_view);
        mRecentSearchRecordAdapter = new RecentSearchRecordAdapter(mRecentSearchRecordList, new RecentSearchRecordAdapter.OnRecentSearchClickListener() {
            @Override
            public void onRecentSearchClick(RecentSearchRecord recentSearchRecord) {
                Intent intent = new Intent();
                intent.putExtra("search_term", recentSearchRecord.getSearchTerm());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mRecentSearchRecordRecyclerView.setAdapter(mRecentSearchRecordAdapter);
        mRecentSearchRecordRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)); //가로로 넘기기
        mRecentSearchRecordRepository = new RecentSearchRecordRepository(this.getApplication());
        new GetRecentSearchRecordTask().execute();
    }

    private class GetRecentSearchRecordTask extends AsyncTask<Void, Void, List<RecentSearchRecord>> {
        @Override
        protected List<RecentSearchRecord> doInBackground(Void... voids) {
            return mRecentSearchRecordRepository.getRecentSearchRecordsList(mRecentSearchLimit);
        }

        @Override
        protected void onPostExecute(List<RecentSearchRecord> recentSearchRecords) {
            super.onPostExecute(recentSearchRecords);
            mRecentSearchRecordList.clear();
            mRecentSearchRecordList.addAll(recentSearchRecords);
            mRecentSearchRecordAdapter.notifyDataSetChanged();
        }
    }
}