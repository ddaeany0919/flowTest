package com.example.flowtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecentSearchRecordAdapter extends RecyclerView.Adapter<RecentSearchRecordAdapter.RecentSearchRecordViewHolder> {
    private final List<RecentSearchRecord> mRecentSearchRecords;
    private final OnRecentSearchClickListener mListener;
    public interface OnRecentSearchClickListener {
        void onRecentSearchClick(RecentSearchRecord recentSearchRecord);
    }

    public RecentSearchRecordAdapter(List<RecentSearchRecord> recentSearchRecords, OnRecentSearchClickListener listener) {
        mRecentSearchRecords = recentSearchRecords;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecentSearchRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_search_record_item, parent, false);
        return new RecentSearchRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentSearchRecordViewHolder holder, int position) {
        RecentSearchRecord recentSearchRecord = mRecentSearchRecords.get(position);
        holder.bind(recentSearchRecord, mListener);
        holder.mSearchRecordTextView.setText(recentSearchRecord.getSearchTerm());
    }

    @Override
    public int getItemCount() {
        return Math.min(mRecentSearchRecords.size(),10);
    }

    public static class RecentSearchRecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView mSearchRecordTextView;

        public RecentSearchRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            mSearchRecordTextView = itemView.findViewById(R.id.recent_search_record_text_view);
        }

        public void bind(final RecentSearchRecord recentSearchRecord, final OnRecentSearchClickListener listener) {
            mSearchRecordTextView.setText(recentSearchRecord.getSearchTerm());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecentSearchClick(recentSearchRecord);
                }
            });
        }
    }
}