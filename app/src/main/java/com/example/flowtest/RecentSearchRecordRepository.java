package com.example.flowtest;

import android.app.Application;
import android.os.AsyncTask;
import java.util.List;

public class RecentSearchRecordRepository {

    private final RecentSearchRecordDao mRecentSearchRecordDao;

    public RecentSearchRecordRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mRecentSearchRecordDao = db.recentSearchRecordDao();
    }

    public void insertRecentSearchRecord(final RecentSearchRecord recentSearchRecord) {
        new InsertRecentSearchRecordAsyncTask(mRecentSearchRecordDao).execute(recentSearchRecord);
    }



    public List<RecentSearchRecord> getRecentSearchRecordsList(int limit) {
        return mRecentSearchRecordDao.getRecentSearchRecordsList(limit);
    }

    private static class InsertRecentSearchRecordAsyncTask extends AsyncTask<RecentSearchRecord, Void, Void> {
        private final RecentSearchRecordDao mRecentSearchRecordDao;

        InsertRecentSearchRecordAsyncTask(RecentSearchRecordDao recentSearchRecordDao) {
            mRecentSearchRecordDao = recentSearchRecordDao;
        }

        @Override
        protected Void doInBackground(RecentSearchRecord... recentSearchRecords) {
            mRecentSearchRecordDao.insertRecentSearchRecord(recentSearchRecords[0]);
            return null;
        }
    }
}