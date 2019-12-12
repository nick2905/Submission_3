package com.dicoding.submission_3;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TVShow implements Parcelable {
    private String tv_name, tv_rate, tv_description, tv_date, tv_ori_lang, tv_photo;

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_rate() {
        return tv_rate;
    }

    public void setTv_rate(String tv_rate) {
        this.tv_rate = tv_rate;
    }

    public String getTv_description() {
        return tv_description;
    }

    public void setTv_description(String tv_description) {
        this.tv_description = tv_description;
    }

    public String getTv_date() {
        return tv_date;
    }

    public void setTv_date(String tv_date) {
        this.tv_date = tv_date;
    }

    public String getTv_ori_lang() {
        return tv_ori_lang;
    }

    public void setTv_ori_lang(String tv_ori_lang) {
        this.tv_ori_lang = tv_ori_lang;
    }

    public String getTv_photo() {
        return tv_photo;
    }

    public void setTv_photo(String tv_photo) {
        this.tv_photo = tv_photo;
    }


    TVShow(JSONObject object){
        try {
            String name = object.getString("name");
            String rate = object.getString("vote_average");
            String description = object.getString("overview");
            String date = object.getString("first_air_date");
            String ori_lang = object.getString("original_language");
            String photo = object.getString("poster_path");


            this.tv_name = name;
            this.tv_rate = rate;
            this.tv_description = description;
            this.tv_date = date;
            this.tv_ori_lang = ori_lang;
            this.tv_photo = photo;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tv_name);
        dest.writeString(this.tv_rate);
        dest.writeString(this.tv_description);
        dest.writeString(this.tv_date);
        dest.writeString(this.tv_ori_lang);
        dest.writeString(this.tv_photo);
    }

    protected TVShow(Parcel in) {
        this.tv_name = in.readString();
        this.tv_rate = in.readString();
        this.tv_description = in.readString();
        this.tv_date = in.readString();
        this.tv_ori_lang = in.readString();
        this.tv_photo = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
