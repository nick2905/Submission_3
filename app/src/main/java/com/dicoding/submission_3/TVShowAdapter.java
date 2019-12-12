package com.dicoding.submission_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.ListViewHolder> {
    private static final String api_photo_url = "https://image.tmdb.org/t/p/w500";
    private Activity activity;
    private Context context;
    private List<TVShow> tvshow = new ArrayList<>();

    TVShowAdapter(Activity activity){
        this.activity = activity;
    }

    void setTVShow(List<TVShow> data){
        this.tvshow.clear();
        this.tvshow.addAll(data);
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView t_name, t_rate, t_description, t_date, t_ori_lang;
        ImageView t_photo;

        ListViewHolder(@NonNull View v) {
            super(v);
            t_name = v.findViewById(R.id.tvshow_name);
            t_rate = v.findViewById(R.id.tvshow_rate);
            t_description = v.findViewById(R.id.tvshow_description);
            t_date = v.findViewById(R.id.tvshow_date);
            t_ori_lang = v.findViewById(R.id.tvshow_original_lang);
            t_photo = v.findViewById(R.id.tvshow_photo);
        }
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tvshow_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final TVShow tvShow = tvshow.get(position);
        Glide.with(holder.itemView.getContext())
                .load(api_photo_url+ tvshow.get(position).getTv_photo())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.t_photo);
        holder.t_name.setText(tvShow.getTv_name());
        holder.t_rate.setText(tvShow.getTv_rate());
        holder.t_description.setText(tvShow.getTv_description());
        holder.t_date.setText(tvShow.getTv_date());
        holder.t_ori_lang.setText(tvShow.getTv_ori_lang());
        context=holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveObjectIntent = new Intent(context,TVDetailActivity.class);
                moveObjectIntent.putExtra(TVDetailActivity.EXTRA_TVSHOW, tvShow);
                context.startActivity(moveObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvshow.size();
    }
}
