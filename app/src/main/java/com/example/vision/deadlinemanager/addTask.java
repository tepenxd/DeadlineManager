package com.example.vision.deadlinemanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.allyants.notifyme.NotifyMe;

import java.util.Calendar;
import java.util.Date;


public class addTask extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private static final String TAG = "whatToDo";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText mTextViewTaskName;
    private EditText mTextViewTaskNote;
    private RelativeLayout addTaskLayout;
    private int mHour,mMinute;
    private TextView txtTime;
    Calendar now = Calendar.getInstance();
    public int day = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        TaskDBHelper dbHelper = new TaskDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        mDisplayDate = (TextView) findViewById(R.id.pickdate_btn);
        txtTime=(TextView)findViewById(R.id.picktime_btn);

        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                final int year = cal.get(Calendar.YEAR);
                final int month = cal.get(Calendar.MONTH);
                final int day = cal.get(Calendar.DAY_OF_MONTH);
                now.set(Calendar.YEAR,year);
                now.set(Calendar.MONTH,month);
                now.set(Calendar.DAY_OF_MONTH,day);


                DatePickerDialog dialog = new DatePickerDialog(
                        addTask.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet : yyyy/mm/dd: " + year + "/" + month + "/" + day);
                String date = year + "/" + month + "/" + day;
                mDisplayDate.setText(date);

            }
        };

        mTextViewTaskName = findViewById(R.id.taskname_txtBox);
        mTextViewTaskNote = findViewById(R.id.notes_txtBox);


    }

    public void addItem(View view) {
        if (mTextViewTaskName.getText().toString().trim().length() == 0 ){
            mTextViewTaskName.setError("required");
            return;

        }

        String taskName = mTextViewTaskName.getText().toString();
        String taskNote = mTextViewTaskNote.getText().toString();
        String dueDate = mDisplayDate.getText().toString();
        String dueTime = txtTime.getText().toString();
        String fullDateTime = dueDate + " " + dueTime + ":00";
        int status = 0;
        long time = now.getTimeInMillis();
        ContentValues cv = new ContentValues();
        cv.put(TaskContract.TaskEntry.COLUMN_TASK_STATUS, status);
        cv.put(TaskContract.TaskEntry.COLUMN_TASK_NAME, taskName);
        cv.put(TaskContract.TaskEntry.COLUMN_TASK_DUE_DATE, time);
        cv.put(TaskContract.TaskEntry.COLUMN_TASK_DUE_DATE_TIME, fullDateTime);
        if (taskNote.matches("")){
            taskNote = "No Description";
        }
        cv.put(TaskContract.TaskEntry.COLUMN_TASK_NOTE, taskNote);
        mDatabase.insert(TaskContract.TaskEntry.TABLE_NAME,null, cv);
        toastSuccess();
        long notif = now.getTimeInMillis() - 259200000 ;
        Date diffDate = new Date(notif);
        String title = "Dont forget about your task";
        NotifyMe.Builder notifyMe = new NotifyMe.Builder(getApplicationContext());
        notifyMe.title(title);
        notifyMe.content(mTextViewTaskName.getText().toString());
        notifyMe.color(255 ,0,0,255);//Color of notification header
        notifyMe.led_color(255,255,255,255);//Color of LED when notification pops up
        notifyMe.time(diffDate);
        notifyMe.large_icon(R.mipmap.ic_launcher_round);
        notifyMe.addAction(new Intent(),"Dissmiss",true,false); //The action will call the intent when pressed
        notifyMe.addAction(new Intent(),"Done");
        notifyMe.build();

    }

    public void toastSuccess(){
        Context context = getApplicationContext();
        CharSequence text = "Task has been saved";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    public void change_color_1(View view) {
        addTaskLayout = findViewById(R.id.addtask_layout);
        addTaskLayout.setBackgroundColor(Color.parseColor("#bcdee9"));
    }

    public void change_color_2(View view) {
        addTaskLayout = findViewById(R.id.addtask_layout);
        addTaskLayout.setBackgroundColor(Color.parseColor("#f4e867"));
    }

    public void change_color_3(View view) {
        addTaskLayout = findViewById(R.id.addtask_layout);
        addTaskLayout.setBackgroundColor(Color.parseColor("#f16748"));
    }

    public void change_color_4(View view) {
        addTaskLayout = findViewById(R.id.addtask_layout);
        addTaskLayout.setBackgroundColor(Color.parseColor("#FFFFAAE4"));
    }

    public void change_color_5(View view) {
        addTaskLayout = findViewById(R.id.addtask_layout);
        addTaskLayout.setBackgroundColor(Color.parseColor("#52b1e7"));
    }

    public void pick_time(View view) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        txtTime.setText(hourOfDay + ":" + minute);
                        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        now.set(Calendar.MINUTE,minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
}
