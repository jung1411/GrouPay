package com.company.groupay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.company.groupay.adapter.GroupAdapter;
import com.company.groupay.models.Events;
import com.company.groupay.models.Group;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String databaseName = "GrouPay";
    private RecyclerView events_list;
    private List<Group> groups;

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groups = new ArrayList<>();
        database = openOrCreateDatabase(databaseName,MODE_PRIVATE,null);
        initDataBaseTables();

        initUI();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(this,AddGroup.class));
        return super.onOptionsItemSelected(item);
    }
    private void initDataBaseTables(){
        String createGroupTable = "CREATE TABLE IF NOT EXISTS groups(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "group_name TEXT NOT NULL, group_count VARCHAR(10) NOT NULL, group_date TEXT NOT NULL,group_cost VARCHAR(5) NOT NULL DEFAULT '0')";
        String createEvent = "CREATE TABLE IF NOT EXISTS events(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "event_name TEXT NOT NULL, event_cost VARCHAR(5) NOT NULL, group_name TEXT NOT NULL)";

        database.execSQL(createGroupTable);
        database.execSQL(createEvent);
    }

    private void initUI(){
        events_list = findViewById(R.id.event_list);
        events_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        String selectAll = "SELECT * FROM groups";
        Cursor dbCursor = database.rawQuery(selectAll,null);
        if(dbCursor.moveToFirst()){
            do{
                Group group = new Group();
                group.setGroup_ID(dbCursor.getInt(0));
                group.setGroup_name(dbCursor.getString(1));
                group.setGroup_number(dbCursor.getString(2));
                group.setGroup_date(dbCursor.getString(3));
                group.setGroup_cost(dbCursor.getString(4));
                groups.add(group);
            }while (dbCursor.moveToNext());
        }

        dbCursor.close();
        events_list.setAdapter(new GroupAdapter(groups,this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    private void reload(){
        groups.clear();
        String selectAll = "SELECT * FROM groups";
        Cursor dbCursor = database.rawQuery(selectAll,null);
        if(dbCursor.moveToFirst()){
            do{
                Group group = new Group();
                group.setGroup_ID(dbCursor.getInt(0));
                group.setGroup_name(dbCursor.getString(1));
                group.setGroup_number(dbCursor.getString(2));
                group.setGroup_date(dbCursor.getString(3));
                group.setGroup_cost(dbCursor.getString(4));
                groups.add(group);
            }while (dbCursor.moveToNext());
        }

        dbCursor.close();
        events_list.setAdapter(new GroupAdapter(groups,this));
    }
}
