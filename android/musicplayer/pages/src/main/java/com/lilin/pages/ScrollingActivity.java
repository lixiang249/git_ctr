package com.lilin.pages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    ArrayList<String> musicList= (ArrayList<String>) MainActivity.list;
    //private ArrayList<String> myMusicList=new ArrayList<String>();
    ListView lv_musicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,"text",Toast.LENGTH_LONG).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //musicList();
    }

    void musicList(){
        File home=new File(MainActivity.MUSIC_PATH);
        File[] f=home.listFiles(new MusicFilter());
        musicList.add("test");
        Toast.makeText(this,"text",Toast.LENGTH_LONG).show();
        if(f.length>0){
            for (File file:f) {
                musicList.add(file.getName());
            }
            lv_musicList= (ListView) findViewById(R.id.lv_musiclist);
            ArrayAdapter<String> musicList_t=new ArrayAdapter<String>(ScrollingActivity.this,
                    android.R.layout.simple_list_item_1,musicList);
            lv_musicList.setAdapter(musicList_t);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        musicList();
        Toast.makeText(ScrollingActivity.this,"test",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
