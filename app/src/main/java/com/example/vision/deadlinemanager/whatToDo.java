package com.example.vision.deadlinemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class whatToDo extends AppCompatActivity {
    private TaskAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    LinearLayout mLinearLayout;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_do);

        mLinearLayout = findViewById(R.id.what_to_do_layout);
        animationDrawable = (AnimationDrawable) mLinearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        TaskDBHelper dbHelper = new TaskDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        int numberOfColumns = 2;
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        mAdapter = new TaskAdapter(this,getAllItems());
        recyclerView.setAdapter(mAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                doneItem((long)viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);



    }

    private void doneItem(long id){
        int status = 1;
        ContentValues cv = new ContentValues();
        cv.put(TaskContract.TaskEntry.COLUMN_TASK_STATUS, status);
        mDatabase.update(TaskContract.TaskEntry.TABLE_NAME, cv, "_id="+id, null);
    }

    private void removeItem(long id){
        mDatabase.delete(TaskContract.TaskEntry.TABLE_NAME,
                TaskContract.TaskEntry._ID + "=" + id, null);

        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems(){
        int status = 0;
        final String whereClause = String.format(TaskContract.TaskEntry.COLUMN_TASK_STATUS + " =" + status );

        return mDatabase.query(
                TaskContract.TaskEntry.TABLE_NAME,
                null,
                whereClause,
                null,
                null,
                null,
                TaskContract.TaskEntry.COLUMN_TASK_DUE_DATE + " ASC"
        );
    }









}
