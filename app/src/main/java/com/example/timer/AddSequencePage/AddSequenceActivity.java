package com.example.timer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timer.RecyclerViewTimer.TimerAdapter;

public class AddSequenceActivity extends AppCompatActivity implements View.OnClickListener {

    private AddSequenceViewModel addSequenceViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSequenceViewModel = new ViewModelProvider(this).get(AddSequenceViewModel.class);
        setContentView(R.layout.activity_add_sequence);

        Intent intent = getIntent();
        Sequence sequence = intent.getParcelableExtra(MainActivity.SEQUENCE);
        addSequenceViewModel.setSequence(sequence);

        Button btnAdd = findViewById(R.id.createButton);
        btnAdd.setOnClickListener(this);

        EditText edit = (EditText)findViewById(R.id.editAddName);
        edit.setText(addSequenceViewModel.getEditName());
        edit.addTextChangedListener();
        edit = (EditText)findViewById(R.id.editAddColor);
        edit.setText(addSequenceViewModel.getEditColor());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTimer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TimerAdapter adapter = new TimerAdapter(this, addSequenceViewModel.getTimers(sequence.getId()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void afterTextChanged(Editable str){
        str.toString()
    }

    @Override
    public void onClick(View view){
        Database db = new Database(getBaseContext());
        EditText edit = (EditText)findViewById(R.id.editAddName);
        String name = edit.getText().toString();
        edit = (EditText)findViewById(R.id.editAddColor);
        String color = edit.getText().toString();
        if(sequence == null) {
            db.addSequence(new Sequence(name, color));
        }
        else{
            sequence.setName(name);
            sequence.setColor(color);
            db.updateSequence(sequence);
        }
        Intent intent = new Intent();
        intent.putExtra("refresh", true);
        setResult(RESULT_OK, intent);
        finish();
    }
}