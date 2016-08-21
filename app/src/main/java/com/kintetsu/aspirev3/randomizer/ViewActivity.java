package com.kintetsu.aspirev3.randomizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.Arrays;

public class ViewActivity extends AppCompatActivity {
    private final String[] mList = {
            "Kuya Mark",
            "Alyssa",
            "Aleli",
            "Nur",
            "Julius"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rv = (RecyclerView) findViewById(R.id.object_view);
        ObjectAdapter oa = new ObjectAdapter(Arrays.asList(mList));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(lm);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(oa);
    }
}
