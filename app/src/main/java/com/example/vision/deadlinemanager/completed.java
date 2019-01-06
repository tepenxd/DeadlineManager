package com.example.vision.deadlinemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class completed extends AppCompatActivity {
    private TaskAdapter2 mAdapter;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        TaskDBHelper dbHelper = new TaskDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        int numberOfColumns = 2;
        RecyclerView recyclerView2 = findViewById(R.id.recycleview_completed);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        mAdapter = new TaskAdapter2(this,getAllDoneItems());
        recyclerView2.setAdapter(mAdapter);
    }


    private Cursor getAllDoneItems(){
        int status = 1;
        final String whereClause2 = String.format(TaskContract.TaskEntry.COLUMN_TASK_STATUS + " =" + status );

        return mDatabase.query(
                TaskContract.TaskEntry.TABLE_NAME,
                null,
                whereClause2,
                null,
                null,
                null,
                TaskContract.TaskEntry.COLUMN_TASK_DUE_DATE + " DESC"
        );
    }
}
