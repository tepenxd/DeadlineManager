package com.example.vision.deadlinemanager;

import android.provider.BaseColumns;

public class TaskContract {

    private TaskContract(){

    }

    public static final class TaskEntry implements BaseColumns{
        public static final String TABLE_NAME = "taskList";
        public static final String COLUMN_TASK_NAME = "taskName";
        public static final String COLUMN_TASK_NOTE = "taskNote";
        public static final String COLUMN_TASK_DUE_DATE = "taskDueDate";
        public static final String COLUMN_TASK_DUE_DATE_TIME = "taskDueDateTime";
        public static final String COLUMN_TASK_STATUS = "taskStatus";

    }
}
