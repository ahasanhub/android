package com.contextidea.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new RecyclerViewAdapter(this,getData()));

    }

    private ArrayList<SportViewModel> getData(){
        ArrayList<SportViewModel> data=new ArrayList<SportViewModel>();
         SportViewModel model1=new SportViewModel();
         model1.sportName="Football";
         model1.imgSrcId.set(R.drawable.star_outline);
         data.add(model1);
        SportViewModel model2=new SportViewModel();
        model2.sportName="Cricket";
        model2.imgSrcId.set(R.drawable.star_outline);
        data.add(model2);
        SportViewModel model3=new SportViewModel();
        model3.sportName="Busket";
        model3.imgSrcId.set(R.drawable.star_outline);
        data.add(model3);
        return data;
    }
}
