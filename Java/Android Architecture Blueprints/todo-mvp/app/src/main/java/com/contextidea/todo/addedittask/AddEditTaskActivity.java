package com.contextidea.todo.addedittask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contextidea.todo.R;

public class AddEditTaskActivity extends AppCompatActivity {
    public static final int REQUEST_ADD_TASK = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
    }
}
