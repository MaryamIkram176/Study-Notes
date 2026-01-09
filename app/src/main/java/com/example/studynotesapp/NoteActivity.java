package com.example.studynotesapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    EditText titleInput, contentInput;
    Button saveBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);
        saveBtn = findViewById(R.id.saveBtn);
        db = new DBHelper(this);

        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String content = contentInput.getText().toString();
            if (!title.isEmpty() && !content.isEmpty()) {
                boolean inserted = db.insertNote(title, content);
                if (inserted) {
                    Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Save typed text on rotation or config change
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", titleInput.getText().toString());
        outState.putString("content", contentInput.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        titleInput.setText(savedInstanceState.getString("title"));
        contentInput.setText(savedInstanceState.getString("content"));
    }
}
