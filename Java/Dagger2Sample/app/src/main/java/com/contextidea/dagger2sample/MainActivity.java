package com.contextidea.dagger2sample;
//https://proandroiddev.com/how-to-dagger-2-with-android-part-1-18b5b941453f
//This is example link
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject MyDateExample myDateExample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvDate=findViewById(R.id.tvDate);
        ((MyApplication)getApplication()).getMyComponent().inject(this);
        tvDate.setText(new Date(myDateExample.GetDate()).toString());
    }
}
