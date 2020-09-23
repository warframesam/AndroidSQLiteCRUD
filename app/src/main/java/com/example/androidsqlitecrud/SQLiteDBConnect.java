package com.example.androidsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.nio.channels.ScatteringByteChannel;

public class SQLiteDBConnect extends SQLiteOpenHelper {

    private String TABLE_NAME;
    public static final String QUERY_CREATE_TABLE = "CREATE TABLE STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(50))";
    public static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS STUDENT";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "simple_crud.db";

    private Context context;

    public SQLiteDBConnect(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(QUERY_CREATE_TABLE);
        }
        catch (Exception e) {
            Toast.makeText(context, "Table Creation Failed!", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(context, "Table Created Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_TABLE);
        onCreate(db);
    }


}
