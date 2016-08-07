package com.kintetsu.aspirev3.randomizer;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button randomize = (Button) findViewById(R.id.randomize);
        final TextView result = (TextView) findViewById(R.id.result);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        randomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Random r = new Random();
                for(int i=0; i<100; i++) {
                    final int index = r.nextInt(mList.length);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(mList[index]);
                        }
                    }, 50*i);
                }
            }
        });
    }

}
