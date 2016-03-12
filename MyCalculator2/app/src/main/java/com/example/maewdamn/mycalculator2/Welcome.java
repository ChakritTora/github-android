package com.example.maewdamn.mycalculator2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Maewdamn on 9/3/2559.
 */
public class Welcome extends Activity {

    //View startButton;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.welcome);

        //startButton = findViewById(R.id.startButton);

        //startButton.setOnClickListener(this);
    }

    /*@Override
    public void onClick(View v) {
        if (v == startButton) {

            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
        }
    }*/

    public void startApp(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
