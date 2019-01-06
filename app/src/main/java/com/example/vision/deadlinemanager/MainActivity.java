package com.example.vision.deadlinemanager;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout mLayout;
    AnimationDrawable animationDrawable;
    TextView mTextView;
    private TaskAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private float AllTaskCount;
    private float TaskCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.cover);
        animationDrawable = (AnimationDrawable) mLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();
        startCountAnimation();




    }

    private void startCountAnimation() {
        TaskDBHelper dbHelper = new TaskDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        mTextView = findViewById(R.id.progress_txt);
        getAllTaskCount();
        getTaskCount();
        float num = (TaskCount / AllTaskCount) * 100;
        int n = Math.round(num);
        int range = 100-n;
        ValueAnimator animator = ValueAnimator.ofInt(0, range);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                mTextView.setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        animator.start();
    }

    public void onOpenWhatToDo_btn(View view) {
        Intent whatToDO = new
                Intent(this,whatToDo.class);
        startActivity(whatToDO);

    }

    public void addTask_btn(View view) {
        Intent addTask = new
                Intent(this,addTask.class);
        startActivity(addTask);

    }

    public void completed_btn(View view) {
        Intent completed = new
                Intent(this,completed.class);
        startActivity(completed);

    }

    public void onSettings_btn(View view) {
        Intent settings = new
                Intent(this,settings.class);
        startActivity(settings);

    }

    public void getTaskCount() {
        String countQuery = "SELECT  * FROM " + TaskContract.TaskEntry.TABLE_NAME + " WHERE " + TaskContract.TaskEntry.COLUMN_TASK_STATUS + "= 0";
        TaskDBHelper dbHelper = new TaskDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        TaskCount = cursor.getCount();
        cursor.close();

    }

    public void getAllTaskCount() {
        String countQuery = "SELECT  * FROM " + TaskContract.TaskEntry.TABLE_NAME;
        TaskDBHelper dbHelper = new TaskDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        AllTaskCount = cursor.getCount();
        cursor.close();
    }



}
