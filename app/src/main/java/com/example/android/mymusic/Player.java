package com.example.android.mymusic;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

public class Player extends AppCompatActivity implements View.OnClickListener {


    static MediaPlayer mp;
    ArrayList<File> myPhoneSongs;
    Uri u;
    Integer position;
    SeekBar sb;
    ImageButton btPlay, btNext, btPrevious;
    Thread updateSeekBar;
    TextView currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btPlay = (ImageButton) findViewById(R.id.btPlay);
        btNext = (ImageButton) findViewById(R.id.btNext);
        btPrevious = (ImageButton) findViewById(R.id.btPrevious);
        currentSong = (TextView) findViewById(R.id.currentSongName);
        sb = (SeekBar) findViewById(R.id.seekBar);


        // Set a click listener to play the audio when the list item is clicked on
        btPlay.setOnClickListener(this);
        btNext.setOnClickListener(this);
        btPrevious.setOnClickListener(this);

        //Update SeekBar
        updateSeekBar = new Thread() {

            @Override
            public void run() {
                int totalDuration = mp.getDuration();
                int currentPosition = 0;
                while (currentPosition < totalDuration) {
                    try {
                        sleep(50000);
                        currentPosition = mp.getCurrentPosition();
                        sb.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // Release music when you back to the song list
        if (mp != null) {
            mp.stop();
            mp.release();
        }

        Intent i = getIntent();
        Bundle b = i.getExtras();
        myPhoneSongs = (ArrayList) b.getParcelableArrayList("songlist");
        position = b.getInt("pos", 0);

        //Universal Resource Indicator
        u = Uri.parse(myPhoneSongs.get(position).toString());
        mp = MediaPlayer.create(getApplicationContext(), u);

        //Set currently plaing song
        position = (position) % myPhoneSongs.size();
        Uri songName = Uri.parse(myPhoneSongs.get(position).getName());
        currentSong.setText(songName.toString().replace(".mp3", "").replace(".mp4", ""));

        // Media Player start- after  click one of song from the list
        mp.start();
        sb.setMax(mp.getDuration());

        updateSeekBar.start();

        //change position on seekBar and change position of Media Player on the song
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());

            }
        });
    }

    // Functionality for 3 buttons ( Prevoius, Play, Next)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btPlay:

                if (mp.isPlaying()) {
                    mp.pause();
                } else mp.start();

                //Set currently plaing song
                position = (position) % myPhoneSongs.size();
                Uri songName = Uri.parse(myPhoneSongs.get(position).getName());
                currentSong.setText(songName.toString().replace(".mp3", "").replace(".mp4", ""));
                break;

            case R.id.btNext:
                mp.stop();
                mp.release();
                position = (position + 1) % myPhoneSongs.size();
                u = Uri.parse(myPhoneSongs.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), u);
                mp.start();
                sb.setMax(mp.getDuration());

                //Set currently plaing song
                Uri songNameN = Uri.parse(myPhoneSongs.get(position).getName().replace(".mp3", "").replace(".mp4", ""));
                currentSong.setText(songNameN.toString());

                break;
            case R.id.btPrevious:
                mp.stop();
                mp.release();
                // ? -"if"  : -"else"
                position = (position - 1 < 0) ? myPhoneSongs.size() - 1 : position - 1;
                u = Uri.parse(myPhoneSongs.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), u);
                mp.start();
                sb.setMax(mp.getDuration());

                //Set currently plaing song
                Uri songNameP = Uri.parse(myPhoneSongs.get(position).getName());
                currentSong.setText(songNameP.toString().replace(".mp3", "").replace(".mp4", ""));
                break;

        }
    }

}

