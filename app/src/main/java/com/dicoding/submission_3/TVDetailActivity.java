package com.dicoding.submission_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class TVDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";

//    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView name, rate, description, date, oriLang;
        ImageView photo;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetail);

//        progressBar= findViewById(R.id.tvshow_progressBar);
//        progressBar.bringToFront();

        TVShow tvshow =getIntent().getParcelableExtra(EXTRA_TVSHOW);

        String tvName = tvshow.getTv_name();
        String tvRate = tvshow.getTv_rate();
        String tvDescription = tvshow.getTv_description();
        String tvDate = tvshow.getTv_date();
        String tvOriLang = tvshow.getTv_ori_lang();

        name = findViewById(R.id.tvshow_name);
        rate = findViewById(R.id.tvshow_rate);
        description = findViewById(R.id.tvshow_description);
        date = findViewById(R.id.tvshow_date);
        oriLang = findViewById(R.id.tvshow_original_lang);
        photo = findViewById(R.id.tvshow_photo);

        name.setText(tvName);
        rate.setText(tvRate);
        description.setText(tvDescription);
        date.setText(tvDate);
        oriLang.setText(tvOriLang);
        String gambarUrl = "https://image.tmdb.org/t/p/w500" + tvshow.getTv_photo();
        Glide.with(TVDetailActivity.this)
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
