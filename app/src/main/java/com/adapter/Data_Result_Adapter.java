package com.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activities.Detail_Activity;
import com.bean.Result;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp.R;
import com.google.gson.Gson;

import java.util.List;

public class Data_Result_Adapter extends RecyclerView.Adapter<Data_Result_Adapter.MyViewHolder> {

    private Context context;
    private List<Result> results_List;

    public Data_Result_Adapter(Context context, List<Result> results_List) {
        this.context = context;
        this.results_List = results_List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data_result_layout, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Result result = results_List.get(position);
        holder.tv_song_name.setText("" + result.getArtistName());
        holder.tv_winner_rank.setText("" + result.getTrackName());

        if (result.getArtworkUrl100() != null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(10));
            Glide.with(context)
                    .load(result.getArtworkUrl100())
                    .apply(requestOptions).into(holder.c_img);
        }

        holder.layout_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = new Gson().toJson(results_List.get(position));
                context.startActivity(new Intent(context, Detail_Activity.class)
                        .putExtra("Data_Object", data)
                );
            }
        });

    }

    @Override
    public int getItemCount() {
        return results_List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout layout_linear;
        private final ImageView c_img;
        private final TextView tv_song_name;
        private final TextView tv_winner_rank;

        public MyViewHolder(@NonNull View view) {
            super(view);
            layout_linear = view.findViewById(R.id.layout_linear);
            c_img = view.findViewById(R.id.c_img);
            tv_song_name = view.findViewById(R.id.tv_winner_name);
            tv_winner_rank = view.findViewById(R.id.tv_winner_rank);
        }
    }
}
