package com.company.groupay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.company.groupay.adapter.ExpensesAdapter;
import com.company.groupay.models.Events;

import java.util.ArrayList;
import java.util.List;

public class GroupExpenses extends AppCompatActivity {

    EditText group_name, group_count;
    SQLiteDatabase database;

    RecyclerView expenses;
    String group_str_name = "UNIVERSAL";
    List<Events> eventsList;
    double totalCost = 0.0;
    int numberOfPeople = 0;

    TextView t_price,t_persons,t_per_person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_expenses);

        database = openOrCreateDatabase(MainActivity.databaseName,MODE_PRIVATE,null);

        eventsList = new ArrayList<>();

        group_name = findViewById(R.id.edt_grp_name);
        group_count = findViewById(R.id.edt_no_people);

        t_per_person = findViewById(R.id.txt_per_person);
        t_persons = findViewById(R.id.txt_total_count);
        t_price = findViewById(R.id.txt_total_cost);

        expenses = findViewById(R.id.expenses_list);
        expenses.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        String dataArray[] = fillInGroupDetails();
        group_count.setText(dataArray[1]);
        group_name.setText(dataArray[0]);

        group_str_name = dataArray[0];
        numberOfPeople = Integer.valueOf(dataArray[1]);

        group_name.setEnabled(false);
        group_count.setEnabled(false);

        addExpenses(group_str_name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        eventsList.clear();
        totalCost = 0.0;
        addExpenses(group_str_name);
    }



    private String[] fillInGroupDetails(){
        Intent mIntent = getIntent();
        String name  = mIntent.getStringExtra("NAME");
        String count = mIntent.getStringExtra("COUNT");
        return new String[]{name,count};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_expense,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String dataArray[] = fillInGroupDetails();
        startActivity(new Intent(this,AddEvent.class).putExtra("NAME",dataArray[0]).putExtra("COST",totalCost));
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    private void addExpenses(String name){
        String query = "SELECT * FROM events WHERE group_name = ?";

        Cursor dbCursor = database.rawQuery(query,new String[]{name});
        if(dbCursor.moveToFirst()) {
            do{
                Events event = new Events();
                event.setEvent_ID(dbCursor.getInt(0));
                event.setEvent_name(dbCursor.getString(1));
                event.setEvent_cost(dbCursor.getString(2));
                event.setGroup_name(dbCursor.getString(3));
                eventsList.add(event);

                totalCost += Double.parseDouble(dbCursor.getString(2));

            }while(dbCursor.moveToNext());
        }
        dbCursor.close();
        expenses.setAdapter(new ExpensesAdapter(this,eventsList));

        t_price.setText("$"+totalCost);
        t_persons.setText(""+numberOfPeople);

        double perPerson = totalCost/numberOfPeople;
        t_per_person.setText("$"+perPerson+" per person");

        String updateGroup = "UPDATE groups SET group_cost = ? WHERE group_name = ?";
        database.execSQL(updateGroup,new Object[]{String.valueOf(totalCost),name});
    }

}
