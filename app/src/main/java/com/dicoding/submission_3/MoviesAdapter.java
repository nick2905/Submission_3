package com.dicoding.submission_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ListViewHolder> {
    private static final String api_photo_url = "https://image.tmdb.org/t/p/w500";
    private Activity activity;
    private Context context;
    private List<Movie> films = new ArrayList<>();

    MoviesAdapter(Activity activity){
    this.activity = activity;
    }

    void setMovie(List<Movie> data){
        this.films.clear();
        this.films.addAll(data);
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView m_name, m_rate, m_description, m_date, m_ori_lang;
        ImageView m_photo;

        ListViewHolder(@NonNull View v){
            super(v);
            m_name = v.findViewById(R.id.txt_name);
            m_rate = v.findViewById(R.id.txt_rate);
            m_description = v.findViewById(R.id.txt_description);
            m_date = v.findViewById(R.id.txt_date);
            m_ori_lang = v.findViewById(R.id.txt_original_lang);
            m_photo = v.findViewById(R.id.img_photo);
        }
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final Movie movie = films.get(position);
        Glide.with(holder.itemView.getContext())
                .load(api_photo_url+ films.get(position).getMv_photo())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.m_photo);
        holder.m_name.setText(movie.getMv_name());
        holder.m_rate.setText(movie.getMv_rate());
        holder.m_description.setText(movie.getMv_description());
        holder.m_date.setText(movie.getMv_date());
        holder.m_ori_lang.setText(movie.getMv_ori_lang());
        context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveObjectIntent = new Intent(context, MovieDetailActivity.class);
                moveObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
                context.startActivity(moveObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }
}
