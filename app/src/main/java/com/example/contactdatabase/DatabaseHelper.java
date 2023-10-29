package com.example.contactdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student";
    private static final String ID_COLUMN_NAME = "student_id";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String BIRTHDAY_COLUMN_NAME = "birthday";
    private static final String EMAIL_COLUMN_NAME = "email";
    private SQLiteDatabase database;
    private static final String DATABASE_CREATE_QUERY = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
                    DATABASE_NAME, ID_COLUMN_NAME, NAME_COLUMN_NAME, BIRTHDAY_COLUMN_NAME, EMAIL_COLUMN_NAME);
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null,1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
    public long inserDetails(String name, String birthday, String email){
        ContentValues rowValues = new ContentValues();
        rowValues.put(NAME_COLUMN_NAME, name);
        rowValues.put(BIRTHDAY_COLUMN_NAME, birthday);
        rowValues.put(EMAIL_COLUMN_NAME, email);
        return database.insertOrThrow(DATABASE_NAME,null,rowValues);
    }
    public String getDetails(){
        Cursor results = database.query("student",
                            new String[]{"student_id","name","birthday","email"},
                            null,null,null,null, "student_id");
        String resultText = "";
        results.moveToFirst();
        while (!results.isAfterLast()){
            int id = results.getInt(0);
            String name = results.getString(1);
            String birthday = results.getString(2);
            String email = results.getString(3);
            resultText += id + " - " + name + "\n" +
                    "\n" +
                    "Birthday: " + birthday + "\n" +
                    "\n" +
                    "Email: "+ email + ",";
            results.moveToNext();
        }
        return resultText;
    }
}
