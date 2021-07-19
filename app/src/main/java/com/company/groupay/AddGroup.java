package com.company.groupay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AddGroup extends AppCompatActivity {

    Button create;
    EditText group_name, group_count;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        database = openOrCreateDatabase(MainActivity.databaseName,MODE_PRIVATE,null);

        create = findViewById(R.id.btn_create_group);

        group_name = findViewById(R.id.edt_grp_name);
        group_count = findViewById(R.id.edt_no_people);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertGroupToDb(Objects.requireNonNull(group_name.getText()).toString(), Objects.requireNonNull(group_count.getText()).toString());
            }
        });
    }

    private void insertGroupToDb(String name, String count){

        Date date = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);

        String insertQuery = "INSERT into groups(group_name,group_count,group_date) VALUES (?,?,?)";
        database.execSQL(insertQuery,new Object[]{name,count,strDate});

        group_name.setText("");
        group_count.setText("");

        startActivity(new Intent(this,GroupExpenses.class).putExtra("NAME",name).putExtra("COUNT",count));


    }

}

