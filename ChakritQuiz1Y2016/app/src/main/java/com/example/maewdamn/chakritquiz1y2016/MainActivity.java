package com.example.maewdamn.chakritquiz1y2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    TextView countLikes;
    String lan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void countLikes(View view) {
        count++;
        countLikes = (TextView) findViewById(R.id.countLikes);
        lan = Locale.getDefault().getDisplayLanguage();
        if (lan == "th") {
            countLikes.setText(count + " จำนวนชอบ");
        } else {
            countLikes.setText(count + " likes");
        }

    }
}
