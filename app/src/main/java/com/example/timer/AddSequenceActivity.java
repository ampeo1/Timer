package com.example.timer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddSequenceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sequence);

        Button btnAdd = findViewById(R.id.createButton);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Database db = new Database(getBaseContext());
        EditText edit = (EditText)findViewById(R.id.editAddName);
        String name = edit.getText().toString();
        edit = (EditText)findViewById(R.id.editAddColor);
        String color = edit.getText().toString();
        db.addSequence(new Sequence(name, color));
    }
}