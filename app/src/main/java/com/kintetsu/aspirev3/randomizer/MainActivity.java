package com.kintetsu.aspirev3.randomizer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final int NUMBER_OF_SPINS = 20;
    public static final int TIME_SPIN = 50;
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
        final Button viewObjects = (Button) findViewById(R.id.view);
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
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                final Random r = new Random();
                randomize.setEnabled(false);
                randomize.setBackgroundColor(useColor(R.color.colorAccent));
                result.setTextColor(useColor(R.color.grey));
                //randomize.setBackgroundColor(getResources().getColor(R.color.grey));
                for (int i = 0; i < NUMBER_OF_SPINS; i++) {
                    final int index = i%mList.length;
                    final int delay = TIME_SPIN * i;

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(mList[index]);
                        }
                    }, delay);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(mList[r.nextInt(mList.length)]);
                        result.setTextColor(useColor(R.color.black));

                        randomize.setEnabled(true);
                        randomize.setBackgroundColor(useColor(R.color.colorAccent));
                        //randomize.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }
                }, TIME_SPIN * NUMBER_OF_SPINS);
            }
        });

        viewObjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(i);
            }
        });
    }

    private int useColor(int id) {
        return ContextCompat.getColor(this, id);
    }
}
