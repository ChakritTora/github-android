package com.example.maewdamn.mycalculator2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {

    double resultNum;
    Bundle cBundle;
    Button secondOKButton;
    EditText secondEditText;
    CoordinateSerializable cSerial;
    CoordinateParcelable cParcelable;
    int x, y, z, x2, y2, z2, x3, y3, z3;
    TextView result_activitySecond, result_bundle, result_serial, result_parcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondOKButton = (Button) findViewById(R.id.secondOKButton);
        secondEditText = (EditText) findViewById(R.id.secondEditText);

        //Intent intent = getIntent();
        //intent.getDoubleExtra("resultNum", resultNum);

        Double resultDouble;
        Bundle extra = getIntent().getExtras();

        resultDouble = extra.getDouble("resultNum");
        cBundle = extra.getBundle("cBundle");

        x = cBundle.getInt("x");
        y = cBundle.getInt("y");
        z = cBundle.getInt("z");

       // cSerial = new CoordinateSerializable();


        cSerial = (CoordinateSerializable) extra.getSerializable("cSerializable");
        x2 = cSerial.x;
        y2 = cSerial.y;
        z2 = cSerial.z;

        cParcelable = (CoordinateParcelable) extra.getParcelable("cParcelable");
        x3 = cParcelable.x;
        y3 = cParcelable.y;
        z3 = cParcelable.z;


        result_activitySecond = (TextView) findViewById(R.id.result_activitySecond);
        result_activitySecond.setText("Result = " + resultDouble);

        result_bundle = (TextView) findViewById(R.id.bundle);
        result_bundle.setText("From Bundle: x = " + x + " y = " + y + " z = " + z);

        result_serial = (TextView) findViewById(R.id.serializable);
        result_serial.setText("From Serializable: x = " + x2 + " y = " + y2 + " z = " + z2);

        result_parcelable = (TextView) findViewById(R.id.parcelable);
        result_parcelable.setText("From Parcelable: x = " + x3 + " y = " + y3 + " z = " + z3);
    }

    public void onClick(View view) {
        if (view == secondOKButton) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", secondEditText.getText().toString());
            setResult(RESULT_OK, returnIntent);
            finish();
        }

    }

}
