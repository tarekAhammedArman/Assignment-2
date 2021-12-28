package com.example.cse438movieappfall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsACtivity extends AppCompatActivity {


    Result result;
    CastInfo CastInfo;

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        result = (Result) getIntent().getSerializableExtra("result");

        ImageView imgView = findViewById(R.id.poster);

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500" + result.getPosterPath())
                .centerCrop()
                .into(imgView);


        TextView textView = findViewById(R.id.moviename);
        textView.setText(result.getTitle());

        TextView textView1 = findViewById(R.id.ratingId);
        textView1.setText(""+result.getVoteAverage());

        TextView textView2 = findViewById(R.id.description);
        textView2.setText(result.getOverview());

        RecyclerView castView = findViewById(R.id.castid);
        castView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        /*try {
            String castInfo = run("https://api.themoviedb.org/3/movie/"+result.getId()+"/credits?api_key=3fa9058382669f72dcb18fb405b7a831");
             CastInfo = new Gson().fromJson(CastInfo, CastInfo.getClass());

            castView.setAdapter(new MyAdapter());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
    class MyAdapter extends RecyclerView.Adapter<CastImage>{

        @NonNull
        @Override
        public CastImage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(DetailsACtivity.this).inflate(R.layout.activity_details, parent, false);
            return new CastImage(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CastImage holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class CastImage extends RecyclerView.ViewHolder{
        ImageView imageView2;
        TextView text;
        public CastImage(@NonNull View itemView) {
            super(itemView);
        }
    }
}