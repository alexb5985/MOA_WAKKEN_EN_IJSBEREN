package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.Face;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gebruiker on 5-12-2016.
 */

class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "wakkenenijsberen";
    // Contacts table name

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SCORE = "CREATE TABLE score (ID INTEGER PRIMARY KEY, Name VARCHAR (255), Goed INTEGER, Fout INTEGER);";
        db.execSQL(CREATE_TABLE_SCORE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + "score");
        // Creating tables again
        onCreate(db);
    }



   public void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();


       //TODO
       // - Timetaken staat nu in score (tijd die je er over gedaan hebt om de score te halen)
       // - Aantal dobbelstenen waarmee je hebt gespeeld
       // - Datum moet ook toegevoegt?
       //TODO
        ContentValues values = new ContentValues();
        values.put("Name", score.getName());
        values.put("Goed", score.getGoodGuesses());
        values.put("Fout", score.getWrongGuesses());

        db.insert("score", null, values);
        db.close(); // Closing database connection
    }
/*
    public Score getScore(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("score", new String[]{"ID",
                        "Name", "Goed","Fout"}, "ID" + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Score score = new Score(cursor.getString(0), Integer.parseInt( cursor.getString(1)), Integer.parseInt((cursor.getString(2))));

        return score;
    } */

    public List<Score> getAllScores() {
        List<Score> scoreList = new ArrayList<Score>();

        String selectQuery = "SELECT * FROM score";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setName(cursor.getString(0));
                score.setGoodGuesses(Integer.parseInt(cursor.getString(1)));
                score.setWrongGuesses(Integer.parseInt(cursor.getString(2)));

                scoreList.add(score);
            } while (cursor.moveToNext());
        }
        return scoreList;
    }
/*
    public int getScoreCount() {
        String countQuery = "SELECT * FROM " + "score";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

            // return count
        return cursor.getCount();
    } */


/*

    public void deleteFavorite(Favorites fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("fav", "ID" + " = ?",
                new String[] { String.valueOf(fav.getId()) });
        db.close();
    } */
}