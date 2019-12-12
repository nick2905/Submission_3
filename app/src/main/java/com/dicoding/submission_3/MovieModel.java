package com.dicoding.submission_3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieModel extends ViewModel {
    public static final String api_movie_url = "https://api.themoviedb.org/3/discover/movie?api_key=8813b7e0484fe59db9797c338f9f5784&language=en-US";
    private MutableLiveData<ArrayList<Movie>> mvList = new MutableLiveData<>();

    public void listMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> mvItem = new ArrayList<>();
        client.get(api_movie_url, new AsyncHttpResponseHandler() {
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            String rMovie = new String(responseBody);
            JSONObject objMov = new JSONObject(rMovie);
            JSONArray LMovie = objMov.getJSONArray("results");
            for(int i = 0 ; i < LMovie.length(); i++){
                JSONObject mv = LMovie.getJSONObject(i);
                Movie IMovie = new Movie(mv);
                mvItem.add(IMovie);
            }
            mvList.postValue(mvItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }
});
    }
        LiveData<ArrayList<Movie>> getMovie(){
        return mvList;
        }
}
