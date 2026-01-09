package com.example.studynotesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "studynotes.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Use _id as primary key for SimpleCursorAdapter compatibility
        db.execSQL("CREATE TABLE notes(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)");
        db.execSQL("CREATE TABLE quiz(_id INTEGER PRIMARY KEY AUTOINCREMENT, score INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        db.execSQL("DROP TABLE IF EXISTS quiz");
        onCreate(db);
    }

    // Insert Note
    public boolean insertNote(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("content", content);
        long result = db.insert("notes", null, cv);
        return result != -1;
    }

    // Fetch all notes
    public Cursor getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT _id, title, content FROM notes", null);
    }

    // Insert quiz score
    public boolean insertScore(int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("score", score);
        long result = db.insert("quiz", null, cv);
        return result != -1;
    }

    // Fetch latest quiz score
    public int getLatestScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT score FROM quiz ORDER BY _id DESC LIMIT 1", null);
        if (cursor.moveToFirst()) {
            int score = cursor.getInt(0);
            cursor.close();
            return score;
        }
        return 0;
    }
}
