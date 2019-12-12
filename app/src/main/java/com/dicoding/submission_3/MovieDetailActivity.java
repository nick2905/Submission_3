package com.dicoding.submission_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private TextView name,rate,description,date,ori_lang;
    private ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String mvName = movie.getMv_name();
        String mvRate = movie.getMv_rate();
        String mvDescription = movie.getMv_description();
        String mvDate = movie.getMv_date();
        String mvOriLang = movie.getMv_ori_lang();

        name = findViewById(R.id.txt_name);
        rate = findViewById(R.id.txt_rate);
        description = findViewById(R.id.txt_description);
        date = findViewById(R.id.txt_date);
        ori_lang = findViewById(R.id.txt_original_lang);
        photo = findViewById(R.id.img_photo);

        name.setText(mvName);
        rate.setText(mvRate);
        description.setText(mvDescription);
        date.setText(mvDate);
        ori_lang.setText(mvOriLang);
        String gambarUrl = "https://image.tmdb.org/t/p/w500" + movie.getMv_photo();
        Glide.with(MovieDetailActivity.this)
                .load(gambarUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(photo);
    }
}
