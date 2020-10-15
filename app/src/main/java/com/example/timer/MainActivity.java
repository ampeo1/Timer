package com.example.timer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Sequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(getApplicationContext());
        ListView list = (ListView)findViewById(R.id.sequences_list_view);
        adapter = new ArrayAdapter<Sequence>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, db.getSequence());
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), SequenceActivity.class);
                intent.putExtra("sequence", adapter.getItem(position));
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.add);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), AddSequenceActivity.class);
                startActivity(intent);
            }
        });
    }
}