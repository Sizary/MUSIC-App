package com.example.android.mymusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;

public class Favorite extends AppCompatActivity implements View.OnClickListener {

    ListView lv;
    public String[] items;
    private MediaPlayer mp;
    ImageButton btPlay, btNext, btPrevious;
    Button btMain;
    ArrayList<File> myPhoneSongs;
    Uri u;
    Integer position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        btPlay = (ImageButton) findViewById(R.id.btPlay);
        btNext = (ImageButton) findViewById(R.id.btNext);
        btPrevious = (ImageButton) findViewById(R.id.btPrevious);
        btMain = (Button) findViewById(R.id.btMain);
        // Set a click listener to play the audio when the list item is clicked on
        btPlay.setOnClickListener(this);
        btNext.setOnClickListener(this);
        btPrevious.setOnClickListener(this);
        btMain.setOnClickListener(this);
        lv = (ListView) findViewById(R.id.lvPlaylist);

        //Array list for phone mp3 or mp4f
        final ArrayList<File> myPhoneSongs = findSongs(Environment.getExternalStorageDirectory());
        myPhoneSongs.add(new File(String.valueOf(R.raw.musicone)));

        items = new String[myPhoneSongs.size()];
        for (int i = 0; i < myPhoneSongs.size(); i++) {
            //toast(myPhoneSongs.get(i).getName().toString());
            //currentlyPlayingSong= myPhoneSongs.get(i).getName();
            //cuting  off  extesion of the file (file name)
            items[i] = myPhoneSongs.get(i).getName().toString().replace(".mp3", "").replace(".mp4", "");
        }
        // adapter knows how to create list items for each item in the list.
        ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(), R.layout.song_layout, R.id.textView2, items);
        lv.setAdapter(adp);

        // Creating working buttons
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Release music when you back to the song list
                if (mp != null) {
                    mp.stop();
                    mp.release();
                }
                //Universal Resource Indicator
                u = Uri.parse(myPhoneSongs.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), u);

                // Media Player start- after  click one of song from the list
                mp.start();

            }
        });
    }

    public ArrayList<File> findSongs(File root) {
        ArrayList<File> al = new ArrayList<>();
        File[] files = root.listFiles();
        if (files != null) {

            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden()) {
                    al.addAll(findSongs(singleFile));
                } else {
                    if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".mp4")) {
                        al.add(singleFile);
                    }
                }
            }
        } else

        {
            Toast.makeText(getApplicationContext(), R.string.upload_song_text, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Player.class);
            startActivity(intent);
        }
        return al;
    }

    @Override
    public void onDestroy() {
        if (mp.isPlaying())
            mp.stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

                break;

            case R.id.btNext:
                /** mp.stop();
                 * mp.release();
                 * position = (position + 1) % myPhoneSongs.size();
                 * u = Uri.parse(myPhoneSongs.get(position).toString());
                 * mp = MediaPlayer.create(getApplicationContext(), u);
                 * mp.start();
                 */
                break;
            case R.id.btPrevious:
                /** mp.stop();
                 mp.release();
                 // ? -"if"  : -"else"
                 position = (position - 1 < 0) ? myPhoneSongs.size() - 1 : position - 1;
                 u = Uri.parse(myPhoneSongs.get(position).toString());
                 mp = MediaPlayer.create(getApplicationContext(), u);
                 mp.start();
                 */
                break;

            case R.id.btMain:
                if (mp.isPlaying()) {
                    mp.pause();
                }
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
        }
    }
}
