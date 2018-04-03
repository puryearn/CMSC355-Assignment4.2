package edu.vcu.puryearna.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "assignment.db";
    private static final String TABLE_NAME = "synonyms";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD1 = "word1";
    private static final String COLUMN_WORD2 = "word2";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table "+TABLE_NAME+" (id integer primary key not null , " +
            "word1 text not null , word2 text not null)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertWordPair(WordPair wp) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from synonyms";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_WORD1, wp.getWord1());
        values.put(COLUMN_WORD2, wp.getWord2());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPair(String searchWord){
        db = this.getReadableDatabase();
        String query = "select word1, word2 from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        Boolean found = false;
        b = "Word not found";

        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);

                if (a.equals(searchWord)) {
                    b = cursor.getString(1);
                    found = true;
                    break;
                }
            } while(cursor.moveToNext());
        }

        if (!found)
            if (cursor.moveToFirst()) {
                do {
                    a = cursor.getString(1);

                    if (a.equals(searchWord)) {
                        b = cursor.getString(0);
                        break;
                    }
                } while (cursor.moveToNext());
            }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

