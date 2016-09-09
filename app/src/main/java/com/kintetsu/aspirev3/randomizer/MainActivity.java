package com.kintetsu.aspirev3.randomizer;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final int NUMBER_OF_SPINS = 20;
    public static final int TIME_SPIN = 50;
    public static final ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button randomize = (Button) findViewById(R.id.randomize);
        final Button viewObjects = (Button) findViewById(R.id.view);
        final TextView result = (TextView) findViewById(R.id.result);

        //populateList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog dialog;
                final EditText etName = new EditText(MainActivity.this);
                etName.setHint("Enter name");

                builder.setMessage("Add new string");
                builder.setView(etName);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name = etName.getText().toString();
                                mList.add(name);

                                Toast.makeText(
                                        MainActivity.this,
                                        "Successfully added " + name,
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.cancel();
                            }
                        });

                dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.show();
            }
        });

        randomize.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(mList.size()>0) {
                    final Random r = new Random();

                    randomize.setEnabled(false);
                    randomize.setBackgroundColor(useColor(R.color.colorAccent));
                    result.setTextColor(useColor(R.color.grey));

                    for (int i = 0; i < NUMBER_OF_SPINS; i++) {
                        final int index = i % mList.size();
                        final int delay = TIME_SPIN * i;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(mList.get(index));
                            }
                        }, delay);
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(mList.get(r.nextInt(mList.size())));
                            result.setTextColor(useColor(R.color.black));

                            randomize.setEnabled(true);
                            randomize.setBackgroundColor(useColor(R.color.colorAccent));
                        }
                    }, TIME_SPIN * NUMBER_OF_SPINS);
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "No string to randomize!",
                            Toast.LENGTH_SHORT).show();
                }
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

    private void populateList() {
        mList.add("Aleli");
        mList.add("Alyssa");
        mList.add("Julius");
        mList.add("Kuya Mark");
        mList.add("Nur");
    }

    private int useColor(int id) {
        return ContextCompat.getColor(this, id);
    }
}
