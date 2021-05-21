package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.Result;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp.R;
import com.google.gson.Gson;

public class Detail_Activity extends AppCompatActivity {

    private TextView tv_country, tv_genre_name, tv_release_Date, tv_price, tv_track_sesored_name, tv_wrapper_type, tv_track_name,
            tv_art_name;
    private ImageView img_profile;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = this;

        init_view();
        set_data();
    }

    private void init_view() {
        tv_country = findViewById(R.id.tv_country);
        tv_genre_name = findViewById(R.id.tv_genre_name);
        tv_release_Date = findViewById(R.id.tv_release_Date);
        tv_price = findViewById(R.id.tv_price);
        tv_track_sesored_name = findViewById(R.id.tv_track_sesored_name);
        tv_wrapper_type = findViewById(R.id.tv_wrapper_type);
        tv_track_name = findViewById(R.id.tv_track_name);
        tv_art_name = findViewById(R.id.tv_art_name);
        img_profile = findViewById(R.id.img_profile);

    }

    @SuppressLint("SetTextI18n")
    private void set_data() {

        Intent intent = getIntent();
        if (intent.hasExtra("Data_Object")) {

            String data = intent.getStringExtra("Data_Object");
            Result result = new Gson().fromJson(data, Result.class);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(10));
            Glide.with(context).load(result.getArtworkUrl100()).apply(requestOptions).into(img_profile);

            tv_art_name.setText("" + result.getArtistName());
            tv_track_name.setText("" + result.getArtistName());
            tv_wrapper_type.setText("" + result.getWrapperType());
            tv_track_sesored_name.setText("" + result.getTrackCensoredName());
            tv_price.setText("" + result.getTrackPrice());
            tv_release_Date.setText("" + result.getReleaseDate());
            tv_genre_name.setText("" + result.getPrimaryGenreName());
            tv_country.setText("" + result.getCountry());


        }

    }
}