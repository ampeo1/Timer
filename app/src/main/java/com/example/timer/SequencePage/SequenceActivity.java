package com.example.timer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timer.Sequence.Sequence;
import com.example.timer.Sequence.Timer.Timer;

import java.util.List;

public class SequenceActivity extends AppCompatActivity {

    ArrayAdapter<Timer> adapter;
    List<Timer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);

        Intent intent = getIntent();
        Sequence sequence = intent.getParcelableExtra(MainActivity.SEQUENCE);
        Database db = new Database(getApplicationContext());
        ListView listView = (ListView)findViewById(R.id.timers_list_view);
        list = db.getTimers(sequence.getId());
        adapter = new ArrayAdapter<Timer>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);
    }
}