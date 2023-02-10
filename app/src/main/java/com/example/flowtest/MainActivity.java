package com.example.flowtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mSearchEditText;
    private Button mSearchButton, mRecentSearchButton;

    private RecyclerView mSearchResultRecyclerView;

    private MovieAdapter mMovieAdapter;
    private List<Movie> mMovieList = new ArrayList<>();
    private AppDatabase mDb;
    private static final String NAVER_API_KEY = "thfCHhOHJj8g5DtOWEA0";
    private static final String NAVER_CLIENT_SECRET = "18ySpKLAfb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText = findViewById(R.id.search_edit_text);
        mSearchButton = findViewById(R.id.search_button);
        mRecentSearchButton = findViewById(R.id.recent_search_button);
        mSearchResultRecyclerView = findViewById(R.id.search_result_recycler_view);

        mMovieAdapter = new MovieAdapter(mMovieList);
        mSearchResultRecyclerView.setAdapter(mMovieAdapter);
        mSearchResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = mSearchEditText.getText().toString();
                if (searchTerm.isEmpty()) {
                    Toast.makeText(MainActivity.this, "공백입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new NaverMovieSearchTask().execute(searchTerm);
                Log.d(searchTerm, searchTerm);

            }
        });

      /*  mRecentSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recentSearchIntent = new Intent(MainActivity.this, RecentSearchActivity.class);
                startActivity(recentSearchIntent);
            }
        });
*/    }
    private class NaverMovieSearchTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String searchTerm = strings[0];
            String result = "";
            try {
                String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + searchTerm;
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", NAVER_API_KEY);
                con.setRequestProperty("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                result = response.toString();
            } catch (Exception e) {
                Log.e("MainActivity", "NaverMovieSearchTask Error", e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray items = jsonObject.getJSONArray("items");
                mMovieList.clear();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    String image = item.getString("image");
                    String title = item.getString("title");
                    String pubDate = item.getString("pubDate");
                    String userRating = item.getString("userRating");

                    Movie movie = new Movie(image,title, pubDate, userRating);
                    mMovieList.add(movie);
                }
                mMovieAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.e("MainActivity", "onPostExecute Error", e);
            }
        }
    }

}