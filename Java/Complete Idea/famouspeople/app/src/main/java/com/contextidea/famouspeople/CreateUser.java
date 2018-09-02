package com.contextidea.famouspeople;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateUser extends AppCompatActivity {
    private static final String TAG = "CreateUser";
    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        email=findViewById(R.id.email);
        final AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production").allowMainThreadQueries().build();
        button=findViewById(R.id.saveUser);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save to database
                Log.d(TAG,"OnClick:First Name"+firstName.getText().toString()+lastName.getText().toString()+email.getText().toString());
                db.userDao().insertAllUsers(new User(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString()));
                startActivity(new Intent(CreateUser.this,MainActivity.class));
            }
        });
    }
}
