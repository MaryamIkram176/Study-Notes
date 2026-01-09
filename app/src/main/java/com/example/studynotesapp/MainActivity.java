package com.example.studynotesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    ListView listView;
    Button addNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        listView = findViewById(R.id.notesListView);
        addNoteBtn = findViewById(R.id.addNoteBtn);

        loadNotes();

        addNoteBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NoteActivity.class));
        });
    }

    private void loadNotes() {
        Cursor cursor = db.getAllNotes();
        String[] from = {"title", "content"};
        int[] to = {R.id.noteTitle, R.id.noteContent};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_note, cursor, from, to, 0);
        listView.setAdapter(adapter);
    }
}
