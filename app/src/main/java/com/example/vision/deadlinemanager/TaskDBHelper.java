package com.example.vision.deadlinemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.example.vision.deadlinemanager.TaskContract.*;

public class TaskDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tasklist.db";
    public static final int DATABASE_VERSION = 1;
    public TaskDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TASKLIST_TABLE = "CREATE TABLE " +
                TaskEntry.TABLE_NAME + " (" +
                TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskEntry.COLUMN_TASK_NAME + " TEXT NOT NULL, " +
                TaskEntry.COLUMN_TASK_NOTE + " TEXT, " +
                TaskEntry.COLUMN_TASK_DUE_DATE + " LONG, " +
                TaskEntry.COLUMN_TASK_DUE_DATE_TIME + " DATETIME, " +
                TaskEntry.COLUMN_TASK_STATUS + " INTEGER  " +
                ");";
        db.execSQL(SQL_CREATE_TASKLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME);
        onCreate(db);
    }
}
