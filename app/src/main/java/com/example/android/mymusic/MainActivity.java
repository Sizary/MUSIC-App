package com.example.android.mymusic;

import android.content.Intent;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    public static String[] items;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lvPlaylist);


        //Array list for phone mp3 or wav
        final ArrayList<File> myPhoneSongs = findSongs(Environment.getExternalStorageDirectory());
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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
         @Override
            public void onItemClick(AdapterView<?>parent, View view, int positoin, long id) {

             startActivity(new Intent(getApplicationContext(), Player.class).putExtra("pos", positoin).putExtra("songlist", myPhoneSongs));


         }});
        }



    public ArrayList<File> findSongs(File root) {
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();

        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                al.addAll(findSongs(singleFile));

            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".mp4")) {
                    al.add(singleFile);
                }
            }
        }
        return al;
    }

    public void toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }


}




