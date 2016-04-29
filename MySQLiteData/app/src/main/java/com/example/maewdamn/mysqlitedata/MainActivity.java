package com.example.maewdamn.mysqlitedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mOutput;
    private DBHelper dh;
    private StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutput = (TextView) findViewById(R.id.tasks);
        dh = new DBHelper(this);
        dh.deleteAll();

        dh.insert(1, "May 9 13:00-16:00 Quiz 2");
        dh.insert(2, "May 17 13:00-16:00 Final exam");
        dh.insert(3, "May 19 13:00-16:00 Final project presentation");
        displayAllRows();

        dh.update(1, "May 9 13:00-16:00 Quiz 2 at IT building");
        mOutput.append("\nUpdating and displaying only the first task\n");
        displayById(1);

        mOutput.append("\nDisplaying all rows\n");
        displayAllRows();
    }

    protected void displayAllRows() {
        dh.selectAll();
        retrieveAllRows();
        mOutput.append(sb.toString());
    }

    protected void displayById(int id) {
        dh.selectById(id);
        retrieveAllRows();
        mOutput.append(sb.toString());
    }

    protected void retrieveAllRows() {
        List<Integer> idList = dh.getIdList();
        List<String> names = dh.getNameList();
        Log.v("MainActivity", idList.toString());
        Log.v("MainActivity", names.toString());
        int i = 0;
        sb = new StringBuilder();
        for (String name : names) {
            Integer id = idList.get(i++);
            sb.append(id + "." + name + "\n");
        }
    }
}
