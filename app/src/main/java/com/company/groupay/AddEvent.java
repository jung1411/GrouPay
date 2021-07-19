package com.company.groupay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class AddEvent extends AppCompatActivity {

    Button add_event;
    EditText event_name, event_cost;
    SQLiteDatabase database;
    double totals = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        database = openOrCreateDatabase(MainActivity.databaseName,MODE_PRIVATE,null);
        totals = getIntent().getDoubleExtra("COST",0.0);
        add_event = findViewById(R.id.btn_add_event);

        event_name = findViewById(R.id.edt_activity_name);
        event_cost = findViewById(R.id.edt_cost);

        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent(Objects.requireNonNull(event_name.getText()).toString(), Objects.requireNonNull(event_cost.getText()).toString());
            }
        });

    }

    private void addEvent(String name, String cost){

        totals += Double.valueOf(cost);
        String updateGroup = "UPDATE groups SET group_cost = ? WHERE group_name = ?";
        database.execSQL(updateGroup,new Object[]{String.valueOf(totals),name});

        String insertEvent = "INSERT into events(event_name,event_cost,group_name) VALUES (?,?,?)";
        database.execSQL(insertEvent,new Object[]{name,cost,getName()});
        event_name.setText("");
        event_cost.setText("");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Event");
        builder.setMessage(name.toUpperCase()+" Added Successfully. \n Do you want to add another event");
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AddEvent.super.onBackPressed();
            }
        });
        builder.setNegativeButton("Yes",null);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String getName(){
        Intent mIntent = getIntent();
        return mIntent.getStringExtra("NAME");
    }


}
