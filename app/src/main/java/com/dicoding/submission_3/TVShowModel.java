package com.dicoding.submission_3;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowModel extends ViewModel {
    public static final String api = "https://api.themoviedb.org/3/discover/tv?api_key=8813b7e0484fe59db9797c338f9f5784&language=en-US";
    private MutableLiveData<ArrayList<TVShow>> tvList = new MutableLiveData<>();
    private String TAG = "ViewModel";

    public void listTVShow() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShow> tvItem = new ArrayList<>();
        Log.d(TAG,"listTVShow" + api);
        client.get(api, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "onSuccess" +responseBody);
                try {
                    String rTVShow = new String(responseBody);
                    JSONObject objTVShow = new JSONObject(rTVShow);
                    JSONArray LTvShow = objTVShow.getJSONArray("results");
                    Log.d(TAG,"onSuccess" + rTVShow);
                    for (int i = 0; i < LTvShow.length(); i++) {
                        JSONObject tv = LTvShow.getJSONObject(i);
                        TVShow IShow = new TVShow(tv);
                        tvItem.add(IShow);
                        Log.d(TAG,"onSuccess" + tv);
                    }
                    tvList.postValue(tvItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG ,error.getMessage());
            }
        });
    }
        LiveData<ArrayList<TVShow>> getTVShow() {
            return tvList;
        }
    }
