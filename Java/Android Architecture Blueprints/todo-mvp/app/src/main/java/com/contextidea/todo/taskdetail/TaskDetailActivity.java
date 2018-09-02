package com.contextidea.todo.taskdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contextidea.todo.R;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "TASK_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
    }
}
