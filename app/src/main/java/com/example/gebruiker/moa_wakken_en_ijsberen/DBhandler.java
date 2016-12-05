package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.Face;

/**
 * Created by Gebruiker on 5-12-2016.
 */

public class DBHandler extends SQLiteOpenHelper {

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



  /*  public void addFavorites(Favorites fav) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Hyperlink", fav.getHyperlink());
        values.put("Omschrijving", fav.getOmschrijving());
        values.put("Catogorie", fav.getCatogorie());
        values.put("DatumAdded", fav.getDatumtoegevoegd());

        db.insert("fav", null, values);
        db.close(); // Closing database connection
    }

    public Favorites getFavorites(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("fav", new String[]{"ID",
                        "Hyperlink", "Omschrijving","Catogorie","DatumAdded" }, "ID" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Favorites fav = new Favorites(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return fav;
    }

    public List<Favorites> getAllFavorites() {
        List<Favorites> favList = new ArrayList<Favorites>();

        //   for(int i = 0; i < 10; i++){
        //      Favorites fav = new Favorites(i, "http://www.google.nl", "Omschrijving", "Nieuws", "date");
        //       favList.add(fav);
        //    }

        String selectQuery = "SELECT * FROM fav";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Favorites fav = new Favorites();
                fav.setId(Integer.parseInt(cursor.getString(0)));
                fav.setHyperlink(cursor.getString(1));
                fav.setOmschrijving(cursor.getString(2));
                fav.setCatogorie(cursor.getString(3));
                fav.setDatumtoegevoegd(cursor.getString(4));

                favList.add(fav);
            } while (cursor.moveToNext());
        }
        return favList;
    }

    public int getFavoritesCount() {
        String countQuery = "SELECT * FROM " + "fav";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }

    public int updateFavorite(Favorites fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", fav.getId());
        values.put("Hyperlink", fav.getHyperlink());
        values.put("Omschrijving", fav.getOmschrijving());
        values.put("Catogorie", fav.getCatogorie());
        values.put("DatumAdded", fav.getDatumtoegevoegd());

// updating row
        return db.update("fav", values, "ID" + "= ?",
                new String[]{String.valueOf(fav.getId())});
    }


    public void deleteFavorite(Favorites fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("fav", "ID" + " = ?",
                new String[] { String.valueOf(fav.getId()) });
        db.close();
    } */
}