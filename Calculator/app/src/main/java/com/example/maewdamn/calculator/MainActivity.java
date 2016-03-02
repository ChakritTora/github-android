package com.example.maewdamn.calculator;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstNum, secondNum;
    TextView result;
    Button button;
    RadioGroup operator;
    RadioButton plus, minus, multiple, devide;
    Switch switch1;
    double resultNum, first, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = (EditText) findViewById(R.id.firstNum);
        secondNum = (EditText) findViewById(R.id.secondNum);
        result = (TextView) findViewById(R.id.result);
        button = (Button) findViewById(R.id.button);
        switch1 = (Switch) findViewById(R.id.switch1);
        operator = (RadioGroup) findViewById(R.id.operator);

        plus = (RadioButton) findViewById(R.id.plus);
        minus = (RadioButton) findViewById(R.id.minus);
        multiple = (RadioButton) findViewById(R.id.multiple);
        devide = (RadioButton) findViewById(R.id.devide);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(MainActivity.this, "Width = " + width + ", Height = " + height, Toast.LENGTH_SHORT).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Switch.OnCheckedChangeListener changeListener = new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean checked) {
                if (checked) {
                    button.setText("ON");
                } else {
                    button.setText("OFF");
                }
            }
        };

        operator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.plus:
                        resultNum = first + second;
                        break;

                    case R.id.minus:
                        resultNum = first - second;
                        break;

                    case R.id.multiple:
                        resultNum = first * second;
                        break;

                    case R.id.devide:
                        try {
                            resultNum = first / second;
                            if (resultNum == Double.POSITIVE_INFINITY || resultNum == Double.NEGATIVE_INFINITY) throw new ArithmeticException();
                            break;

                        } catch (ArithmeticException ae) {
                            showToast("Please devide by a non-zero number");
                            resultNum = 0;

                        }
                }

                result.setText("= " + resultNum);
            }
        });

        button.setOnClickListener(this);
        switch1.setOnCheckedChangeListener(changeListener);

    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    private void acceptNumbers(int[] val) {

    }

    public void onClick(View view) {
        if (view == button) {
            try {
                first = Double.parseDouble(firstNum.getText().toString());

            } catch (NumberFormatException e1) {
                showToast("Please enter only a number");
            }

            try {
                second = Double.parseDouble(secondNum.getText().toString());

            } catch (NumberFormatException e2) {
                showToast("Please enter only a number");
            }

            long tStart = System.currentTimeMillis();
            Log.d("Calculation", "computation time = " + 0.0);


            switch (operator.getCheckedRadioButtonId()) {
                case R.id.plus:
                    resultNum = first + second;
                    break;

                case R.id.minus:
                    resultNum = first - second;
                    break;

                case R.id.multiple:
                    resultNum = first * second;
                    break;

                case R.id.devide:
                    try {
                        resultNum = first / second;
                        if (resultNum == Double.POSITIVE_INFINITY || resultNum == Double.NEGATIVE_INFINITY) throw new ArithmeticException();
                        break;

                    } catch (ArithmeticException ae) {
                        Toast toast = Toast.makeText(MainActivity.this, "Please devide by a non-zero number", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                        resultNum = 0;

                    }
            }

            long tEnd = System.currentTimeMillis();
            long tTime = tEnd - tStart;
            double elapsedSeconds = tTime / 1000.0;
            Log.d("Calculation", "computation time = " + elapsedSeconds);


            result.setText("= " + resultNum);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(MainActivity.this, "Setting selected", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return true;
        }

        if (id == R.id.action_refresh) {
            Toast toast = Toast.makeText(MainActivity.this, "Refresh selected", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
