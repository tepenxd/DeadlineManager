package com.example.vision.deadlinemanager;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskAdapter2 extends RecyclerView.Adapter<TaskAdapter2.TaskViewHolder>{
    private Context mContext;
    private Cursor mCursor;
    public TaskAdapter2(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }
    public class TaskViewHolder extends RecyclerView.ViewHolder{
        public TextView taskName;

        public TextView taskNote;
        public TextView taskDueDateTime;
        public TaskViewHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name);
            taskNote = itemView.findViewById(R.id.task_note);
            taskDueDateTime = itemView.findViewById(R.id.task_due_date_time);

        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        long id = mCursor.getLong(mCursor.getColumnIndex(TaskContract.TaskEntry._ID));
        String taskName = mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TASK_NAME));
        String taskNote = mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TASK_NOTE));
        String taskDateTime = mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TASK_DUE_DATE));
        holder.taskName.setText(taskName);
        holder.taskNote.setText(taskNote);
        holder.taskDueDateTime.setText(taskDateTime);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();

        }
        mCursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
