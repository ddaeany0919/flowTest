package com.example.flowtest;

public class Movie {
    private final String mImage;
    private final String mTitle;
    private final String mPubDate;
    private final String mUserRating;


    public Movie(String image,String title, String pubDate, String userRating) {
        mImage = image;
        mTitle = title;
        mPubDate = pubDate;
        mUserRating = userRating;
    }

    public String getImage() {
        return mImage;
    }
    public String getTitle() {
        return mTitle;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public String getUserRating() {
        return mUserRating;
    }


}