package com.contextidea.famouspeople;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
   // ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        users=new ArrayList<User>();
//
//        for (int i = 0; i < 10; i++) {
//            User user=new User("Ahasan"+i,"Habib","shobuz@yahoo.com");
//            users.add(user);
//        }
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production").allowMainThreadQueries().build();
        List<User> users=db.userDao().getAllUsers();
        recyclerView=findViewById(R.id.recylcer_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new UserAdapter(users);
        recyclerView.setAdapter(adapter);
        //adapter=new UserAdapter1();
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: prssed!");
                startActivity(new Intent(MainActivity.this,CreateUser.class));
            }
        });
    }

}
