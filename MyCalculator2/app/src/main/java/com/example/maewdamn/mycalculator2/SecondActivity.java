package com.example.maewdamn.mycalculator2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    double resultNum;
    TextView result_activitySecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Intent intent = getIntent();
        //intent.getDoubleExtra("resultNum", resultNum);

        Double resultDouble;
        Bundle extra = getIntent().getExtras();
        resultDouble = extra.getDouble("resultNum");

        //resultString = Double.toString(resultNum);

        result_activitySecond = (TextView) findViewById(R.id.result_activitySecond);
        result_activitySecond.setText("Result = " + resultDouble);
    }

}
