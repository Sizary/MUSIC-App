package com.example.android.mymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView serchButton = (ImageView) findViewById(R.id.searchButton);
        ImageView artist = (ImageView) findViewById(R.id.artist);
        ImageView favorite = (ImageView) findViewById(R.id.favorite);
        ImageView pop = (ImageView) findViewById(R.id.pop);
        ImageView rock = (ImageView) findViewById(R.id.rock);
        ImageView house = (ImageView) findViewById(R.id.house);
        ImageView hiphop = (ImageView) findViewById(R.id.hipHop);
        ImageView clasic = (ImageView) findViewById(R.id.clasic);
        ImageView allSongs = (ImageView) findViewById(R.id.allSongs);
        ImageView radio = (ImageView) findViewById(R.id.radio2);

        serchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Search.class);
                startActivity(intent);
            }
        });

        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Artist.class);
                startActivity(intent);
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Favorite.class);
                startActivity(intent);
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Geners.class);
                startActivity(intent);
            }
        });
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Geners.class);
                startActivity(intent);
            }
        });
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Geners.class);
                startActivity(intent);
            }
        });
        hiphop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Geners.class);
                startActivity(intent);
            }
        });
        clasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Geners.class);
                startActivity(intent);
            }
        });
        allSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllSongs.class);
                startActivity(intent);
            }
        });
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Radio.class);
                startActivity(intent);
            }
        });
    }
}




